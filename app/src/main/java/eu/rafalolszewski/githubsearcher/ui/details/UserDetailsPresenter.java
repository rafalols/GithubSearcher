package eu.rafalolszewski.githubsearcher.ui.details;

import android.os.Bundle;
import android.util.Log;

import org.parceler.Parcels;

import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import eu.rafalolszewski.githubsearcher.model.GithubUser;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by rafal on 23.05.16.
 */

public class UserDetailsPresenter implements UserDetailsVP.Presenter {

    private static final String TAG = "UserDetailsPresenter";

    private UserDetailsActivity activity;
    private UserDetailsVP.View view;

    private GitHubApi gitHubApi;

    private GithubUser user;

    public UserDetailsPresenter(UserDetailsActivity activity, UserDetailsVP.View view) {
        this.activity = activity;
        this.view = view;

        gitHubApi = activity.getApi();
    }

    @Override
    public void getUserByName(String name) {
        gitHubApi.getUser(name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(u -> onGetUser(u));
    }

    private void onGetUser(GithubUser user) {
        Log.i(TAG, "onGetUser: ");
        this.user = user;
        view.onGetUser(user);
        view.setProgressIndicator(false);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        view.setProgressIndicator(true);
        if (savedInstanceState != null){
            user = Parcels.unwrap(savedInstanceState.getParcelable(ARG_USERNAME));
            onGetUser(user);
        }else if (activity.getIntent().hasExtra(ARG_USERNAME)){
            String name = activity.getIntent().getStringExtra(ARG_USERNAME);
            getUserByName(name);
        }
    }

    @Override
    public void onSave(Bundle outState) {
        outState.putParcelable(ARG_USERNAME, Parcels.wrap(user));
    }

}
