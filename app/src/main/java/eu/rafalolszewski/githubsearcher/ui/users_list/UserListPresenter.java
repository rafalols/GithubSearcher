package eu.rafalolszewski.githubsearcher.ui.users_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
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
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by rafal on 02.05.16.
 */
public class UserListPresenter implements UserListVP.Presenter {

    private static final String TAG = "UserListPresenter";
    private static final String SEARCH_RESULT = "searchResults";
    private static final String AVATAR = "avatar";

    private UserListActivity activity;
    private UserListVP.View view;
    private HistoryDao historyDao;

    private GitHubApi gitHubApi;

    private GithubUsersSearch cashedUserSearch;
    private Map<String, GithubUser> cashedUsersDetails;

    public UserListPresenter(UserListActivity userListActivity, UserListVP.View view, HistoryDao historyDao) {
        this.activity = userListActivity;
        this.view = view;
        this.historyDao = historyDao;

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
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(userList -> onGetUsersList(userList, searchString),
                            err -> onApiError(err));
        }
    }

    private void onApiError(Throwable e) {
        toast(activity.getString(R.string.api_error));
        Log.e(TAG, "onError: ", e);
    }

    @Override
    public void clickUser(String name, View avatarForTransition) {
        Intent intent = new Intent(activity, UserDetailsActivity.class);
        intent.putExtra(UserDetailsVP.Presenter.ARG_USERNAME, name);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, avatarForTransition, AVATAR);

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
