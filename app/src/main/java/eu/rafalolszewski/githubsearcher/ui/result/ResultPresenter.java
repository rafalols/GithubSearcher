package eu.rafalolszewski.githubsearcher.ui.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.Toast;

import org.parceler.Parcels;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import eu.rafalolszewski.githubsearcher.dao.HistoryDao;
import eu.rafalolszewski.githubsearcher.model.GithubRepoSearch;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import eu.rafalolszewski.githubsearcher.ui.details.UserDetailsActivity;
import eu.rafalolszewski.githubsearcher.ui.details.UserDetailsVP;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by Rafał Olszewski (rafal.olszewski@hycom.pl) on 04.08.16.
 */
public class ResultPresenter implements ResultVP.Presenter {

    public static final String SEARCH_RESULT = "searchResults";
    private static final String AVATAR = "avatar";
    private static final String LOGIN = "login";
    private static final int USERS = 0;
    private static final int REPOS = 1;

    ResultVP.View view;
    GitHubApi api;
    private HistoryDao historyDao;
    private Scheduler observeOnScheduler;
    private GithubUsersSearch cashedUserSearch;
    CountingIdlingResource countingIdlingResource;

    private int usersCount = -1;
    private int reposCount = -1;

    public ResultPresenter(ResultVP.View view, GitHubApi api, HistoryDao historyDao, Scheduler observeOnScheduler,
                           CountingIdlingResource countingIdlingResource) {
        this.view = view;
        this.api = api;
        this.historyDao = historyDao;
        this.observeOnScheduler = observeOnScheduler;
        this.countingIdlingResource = countingIdlingResource;
    }



    @Override
    public void search(String searchString) {
        if (countingIdlingResource != null) {
            countingIdlingResource.increment();
            countingIdlingResource.increment();
        }
        resetCounts();
        if (api == null){
            toast(view.getActivity().getString(R.string.wait_for_api));
        }else{
            view.setUsersProgressIndicator(true);
            api.searchForUsers(searchString)
                    .subscribeOn(Schedulers.io())
                    .observeOn(observeOnScheduler)
                    .doOnNext(userList -> onGetUsersList(userList, searchString))
                    .doOnError(er -> onUsersApiError(er))
                    .subscribe();

            view.setReposProgressIndicator(true);
            api.searchForRepos(searchString)
                    .subscribeOn(Schedulers.io())
                    .observeOn(observeOnScheduler)
                    .subscribe(
                        reposList -> onGetReposList(reposList, searchString),
                        error -> onReposApiError(error)
                    );

        }
    }

    private void resetCounts() {
        usersCount = -1;
        reposCount = -1;
    }

    private void onGetReposList(GithubRepoSearch reposList, String searchString) {
        view.setLoadUsersError(false);
        view.setReposProgressIndicator(false);
        setCountAndSaveSearchToHistory(REPOS, searchString, reposList.count);
        view.refreshRepos(reposList);
        if (countingIdlingResource != null) countingIdlingResource.decrement();
    }

    private void onGetUsersList(GithubUsersSearch usersSearch, String searchString){
        view.setLoadUsersError(false);
        view.setUsersProgressIndicator(true);
        setCountAndSaveSearchToHistory(USERS, searchString, usersSearch.count);
        view.refreshUsers(usersSearch);
        if (countingIdlingResource != null) countingIdlingResource.decrement();
    }

    private void setCountAndSaveSearchToHistory(int type, String searchString, int count){
        if (type == USERS) usersCount = count;
        if (type == REPOS) reposCount = count;
        if (usersCount != -1 && reposCount != -1){
            historyDao.putSearchToHistory(searchString, usersCount, reposCount);
        }
    }

    private void onReposApiError(Throwable error) {
        view.setLoadReposError(true);
    }

    private void onUsersApiError(Throwable e) {
        view.setLoadUsersError(true);
    }


    private void toast(String errorString) {
        Toast.makeText(view.getActivity(), errorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickUser(String name, View avatarForTransition, View loginForTransition) {
        Intent intent = new Intent(view.getActivity(), UserDetailsActivity.class);
        intent.putExtra(UserDetailsVP.Presenter.ARG_USERNAME, name);

        Pair<View, String> p1 = Pair.create(avatarForTransition, AVATAR);
        Pair<View, String> p2 = Pair.create(loginForTransition, LOGIN);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(view.getActivity(), p1, p2);

        view.getActivity().startActivity(intent, options.toBundle());
    }

    @Override
    public void clickRepo() {

    }

    @Override
    public void onRestoreInstance(Bundle savedInstanceState) {
        cashedUserSearch = Parcels.unwrap(savedInstanceState.getParcelable(SEARCH_RESULT));
        view.refreshUsers(cashedUserSearch);
    }

    @Override
    public void onSaveInstance(Bundle outState) {
        if (cashedUserSearch != null) {
            outState.putParcelable(SEARCH_RESULT, Parcels.wrap(cashedUserSearch));
        }
    }
}
