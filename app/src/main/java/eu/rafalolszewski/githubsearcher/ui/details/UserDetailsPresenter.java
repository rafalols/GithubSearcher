package eu.rafalolszewski.githubsearcher.ui.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import org.parceler.Parcels;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import eu.rafalolszewski.githubsearcher.model.GithubUser;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by rafal on 23.05.16.
 */

public class UserDetailsPresenter implements UserDetailsVP.Presenter {

    private static final String TAG = "UserDetailsPresenter";


    private UserDetailsActivity activity;
    private UserDetailsVP.View view;
    private Scheduler observeOnScheduler;

    private GitHubApi gitHubApi;

    private GithubUser user;

    public UserDetailsPresenter(UserDetailsActivity activity, UserDetailsVP.View view, Scheduler observeOnScheduler) {
        this.activity = activity;
        this.view = view;
        this.observeOnScheduler = observeOnScheduler;

        gitHubApi = activity.getApi();
    }

    @Override
    public void getUserByLogin(String login) {
        gitHubApi.getUser(login)
                .subscribeOn(Schedulers.newThread())
                .observeOn(observeOnScheduler)
                .doOnNext(u -> onGetUser(u))
                .doOnError(e -> onApiError(e))
                .subscribe();
    }

    private void onApiError(Throwable e) {
        view.setLoadDataError(e);
        Log.e(TAG, "onApiError: ", e);
    }

    @Override
    public void onLoadedImage(boolean succedd) {
        activity.whenImageIsLoaded();
    }

    @Override
    public void onTransitionFinished() {
        view.animateFabButton();
    }

    @Override
    public void openUserProfile() {
        String url = activity.getString(R.string.github_url) + user.login;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
    }

    private void onGetUser(GithubUser user) {
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
            getUserByLogin(name);
        }
    }

    @Override
    public void onSave(Bundle outState) {
        outState.putParcelable(ARG_USERNAME, Parcels.wrap(user));
    }

}
