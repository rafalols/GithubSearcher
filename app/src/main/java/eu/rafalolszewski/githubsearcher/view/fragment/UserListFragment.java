package eu.rafalolszewski.githubsearcher.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.model.GithubUser;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import eu.rafalolszewski.githubsearcher.view.presenter.UserListPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserListFragment extends Fragment implements UserListView{

    @Inject
    UserListPresenter presenter;

    private static final String TAG = "UserListFragment";

    public UserListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        return view;
    }


    @Override
    public void onGetUsersList(GithubUsersSearch usersList) {
        Log.i(TAG, "onGetUsersList: get " + usersList.count + "users");
    }

    @Override
    public void onRefreshUser(GithubUser user) {
        Log.i(TAG, "onRefreshUser:  name" + user.name
                + ", email " + user.email
                + ", company " + user.company
                + ", type " + user.type
                + ", fallowers " + user.followers
                + ", repos " + user.publicRepos
                + ", htmlUrl " + user.htmlUrl);
    }

    @Override
    public void setProgressIndicator(boolean enabled) {

    }

    @Override
    public void setLoadDataError(Throwable t) {

    }

    @Override
    public void initViewToPresenter() {
        presenter.setView(this);
    }
}
