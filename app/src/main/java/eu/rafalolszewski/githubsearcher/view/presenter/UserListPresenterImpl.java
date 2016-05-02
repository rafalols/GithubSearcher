package eu.rafalolszewski.githubsearcher.view.presenter;

import android.os.Bundle;

import eu.rafalolszewski.githubsearcher.view.activity.UserListActivity;
import eu.rafalolszewski.githubsearcher.view.fragment.UserListView;

/**
 * Created by rafal on 02.05.16.
 */
public class UserListPresenterImpl implements UserListPresenter {

    UserListActivity userListActivity;
    UserListView userListView;

    public UserListPresenterImpl(UserListActivity userListActivity, UserListView userListView) {
        this.userListActivity = userListActivity;
        this.userListView = userListView;
    }

    @Override
    public void getUserList(String searchString) {

    }

    @Override
    public void clickUser(int userId) {

    }

    @Override
    public void showHideUsersDetails(int userId, boolean show) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onSave(Bundle bundle) {

    }

    @Override
    public void onStop() {

    }
}
