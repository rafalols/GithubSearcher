package eu.rafalolszewski.githubsearcher.view.presenter;

import android.os.Bundle;
import android.widget.Toast;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import eu.rafalolszewski.githubsearcher.model.GithubUser;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import eu.rafalolszewski.githubsearcher.view.activity.UserListActivity;
import eu.rafalolszewski.githubsearcher.view.fragment.BaseView;
import eu.rafalolszewski.githubsearcher.view.fragment.UserListView;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by rafal on 02.05.16.
 */
public class UserListPresenterImpl implements UserListPresenter {

    UserListActivity activity;
    UserListView userListView;

    GitHubApi gitHubApi;

    public UserListPresenterImpl(UserListActivity userListActivity, UserListView userListView) {
        this.activity = userListActivity;
        this.userListView = userListView;

        gitHubApi = userListActivity.getApi();
    }

    @Override
    public void getUserList(String searchString) {
        if (gitHubApi == null){
            Toast.makeText(activity, activity.getString(R.string.wait_for_api), Toast.LENGTH_LONG).show();
        }else{
            userListView.setProgressIndicator(true);
            gitHubApi.searchForUsers(searchString)
                    .subscribe(usersSearch -> onGetUsersList(usersSearch));
        }
    }

    private void onGetUsersList(GithubUsersSearch usersSearch){
        userListView.setProgressIndicator(false);
        userListView.onGetUsersList(usersSearch);
        searchForUsersDetails(usersSearch);
    }

    private void searchForUsersDetails(GithubUsersSearch usersSearch) {
        Observable.from(usersSearch.usersPreviews)
                .map(user -> user.login)
                .flatMap(login -> gitHubApi.getUser(login))
                .subscribe(user -> onGetUserDetails(user));
    }

    private void onGetUserDetails(GithubUser user) {
        userListView.onRefreshUser(user);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null){
            //TODO: get instance state, and refresh view
        }else if (activity.getIntent().hasExtra(SearchPresenter.SEARCH_STRING)){
            String searchString = activity.getIntent().getStringExtra(SearchPresenter.SEARCH_STRING);
            getUserList(searchString);
        }
    }

    @Override
    public void onSave(Bundle bundle) {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setView(BaseView view) {
        userListView = (UserListView) view;
    }
}
