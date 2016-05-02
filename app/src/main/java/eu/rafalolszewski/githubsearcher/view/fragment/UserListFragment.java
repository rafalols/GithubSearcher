package eu.rafalolszewski.githubsearcher.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.model.GithubUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserListFragment extends Fragment implements UserListView{


    public UserListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    @Override
    public void refreshUsers(List<GithubUser> listOfUsers) {

    }

    @Override
    public void setProgressIndicator(boolean enabled) {

    }

    @Override
    public void setLoadDataError(Throwable t) {

    }
}
