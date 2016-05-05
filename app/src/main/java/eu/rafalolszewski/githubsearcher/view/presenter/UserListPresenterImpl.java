package eu.rafalolszewski.githubsearcher.view.presenter;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.HashMap;
import java.util.Map;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import eu.rafalolszewski.githubsearcher.dao.HistoryDao;
import eu.rafalolszewski.githubsearcher.model.GithubUser;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import eu.rafalolszewski.githubsearcher.view.activity.UserListActivity;
import eu.rafalolszewski.githubsearcher.view.fragment.BaseView;
import eu.rafalolszewski.githubsearcher.view.fragment.UserListView;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by rafal on 02.05.16.
 */
public class UserListPresenterImpl implements UserListPresenter {

    private static final String TAG = "UserListPresenterImpl";
    private static final String SEARCH_RESULT = "searchResults";

    UserListActivity activity;
    UserListView view;
    HistoryDao historyDao;

    GitHubApi gitHubApi;

    GithubUsersSearch cashedUserSearch;
    Map<String, GithubUser> cashedUsersDetails;

    public UserListPresenterImpl(UserListActivity userListActivity, UserListView view, HistoryDao historyDao) {
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
                    .subscribe(userList -> onGetUsersList(userList),
                            err -> onApiError(err));
        }
    }

    private void onApiError(Throwable e) {
        toast(activity.getString(R.string.api_error));
        Log.e(TAG, "onError: ", e);
    }

    @Override
    public void clickUser(String name) {
        //TODO: Open activity with user details
    }

    private void onGetUsersList(GithubUsersSearch usersSearch){
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
        }else if (activity.getIntent().hasExtra(SearchPresenter.SEARCH_STRING)){
            String searchString = activity.getIntent().getStringExtra(SearchPresenter.SEARCH_STRING);
            getUserList(searchString);
        }
    }

    @Override
    public void onSave(Bundle outState) {
        if (cashedUserSearch != null) {
            outState.putParcelable(SEARCH_RESULT, Parcels.wrap(cashedUserSearch));
        }
    }

    @Override
    public void onStop() {

    }

    @Override
    public void setView(BaseView view) {
        this.view = (UserListView) view;
    }
}
