package eu.rafalolszewski.githubsearcher.ui.users_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.HashMap;
import java.util.Map;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import eu.rafalolszewski.githubsearcher.dao.HistoryDao;
import eu.rafalolszewski.githubsearcher.model.GithubUser;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import eu.rafalolszewski.githubsearcher.ui.details.UserDetailsActivity;
import eu.rafalolszewski.githubsearcher.ui.details.UserDetailsVP;
import eu.rafalolszewski.githubsearcher.ui.search.SearchVP;
import rx.Scheduler;

/**
 * Created by rafal on 02.05.16.
 */
public class UserListPresenter implements UserListVP.Presenter {

    private static final String TAG = "UserListPresenter";
    private static final String SEARCH_RESULT = "searchResults";
    private static final String AVATAR = "avatar";
    private static final String LOGIN = "login";

    private UserListActivity activity;
    private UserListVP.View view;
    private HistoryDao historyDao;
    private Scheduler observeOnScheduler;

    private GitHubApi gitHubApi;

    private GithubUsersSearch cashedUserSearch;
    private Map<String, GithubUser> cashedUsersDetails;

    public UserListPresenter(UserListActivity userListActivity, UserListVP.View view, HistoryDao historyDao, Scheduler observeOnScheduler) {
        this.activity = userListActivity;
        this.view = view;
        this.historyDao = historyDao;
        this.observeOnScheduler = observeOnScheduler;

        cashedUsersDetails = new HashMap<>();

        gitHubApi = userListActivity.getApi();
    }

    @Override
    public void getUserList(String searchString) {
        if (gitHubApi == null){
            toast(activity.getString(R.string.wait_for_api));
        }else{
            view.setProgressIndicator(true);
            gitHubApi.searchForUsers(searchString)
                    .observeOn(observeOnScheduler)
                    .subscribe(userList -> onGetUsersList(userList, searchString),
                            err -> onApiError(err));
        }
    }

    private void onApiError(Throwable e) {
        toast(activity.getString(R.string.api_error));
        Log.e(TAG, "onError: ", e);
    }

    @Override
    public void clickUser(String name, View avatarForTransition, View loginForTransition) {
        Intent intent = new Intent(activity, UserDetailsActivity.class);
        intent.putExtra(UserDetailsVP.Presenter.ARG_USERNAME, name);

        Pair<View, String> p1 = Pair.create(avatarForTransition, AVATAR);
        Pair<View, String> p2 = Pair.create(loginForTransition, LOGIN);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, p1, p2);

        activity.startActivity(intent, options.toBundle());
    }

    private void onGetUsersList(GithubUsersSearch usersSearch, String searchString){
        historyDao.putSearchToHistory(searchString, usersSearch.count);
        view.setProgressIndicator(false);
        view.onGetUsersList(usersSearch);
    }

    private void toast(String errorString) {
        Toast.makeText(activity, errorString, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(SEARCH_RESULT)){
            cashedUserSearch = Parcels.unwrap(savedInstanceState.getParcelable(SEARCH_RESULT));
            view.onGetUsersList(cashedUserSearch);
        }else if (activity.getIntent().hasExtra(SearchVP.Presenter.SEARCH_STRING)){
            String searchString = activity.getIntent().getStringExtra(SearchVP.Presenter.SEARCH_STRING);
            getUserList(searchString);
        }
    }

    @Override
    public void onSave(Bundle outState) {
        if (cashedUserSearch != null) {
            outState.putParcelable(SEARCH_RESULT, Parcels.wrap(cashedUserSearch));
        }
    }

}
