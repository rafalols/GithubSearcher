package eu.rafalolszewski.githubsearcher.ui.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.parceler.Parcels;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import eu.rafalolszewski.githubsearcher.model.UserDetails;
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

    private UserDetails user;

    public UserDetailsPresenter(UserDetailsActivity activity, UserDetailsVP.View view, Scheduler observeOnScheduler) {
        this.activity = activity;
        this.view = view;
        this.observeOnScheduler = observeOnScheduler;

        gitHubApi = activity.getApi();
    }

    @Override
    public void getUserByLogin(String login) {
        if (gitHubApi == null){
            toast(activity.getString(R.string.wait_for_api));
        }else {
            view.setProgressIndicator(true);
            gitHubApi.getUser(login)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(observeOnScheduler)
                    .doOnNext(u -> sendUserToView(u))
                    .doOnError(e -> onApiError(e))
                    .subscribe();
        }
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

    private void sendUserToView(UserDetails user) {
        this.user = user;
        view.onGetUser(user);
        view.setProgressIndicator(false);
    }


    @Override
    public void onRestoreInstance(Bundle savedInstanceState) {
        user = Parcels.unwrap(savedInstanceState.getParcelable(ARG_USERNAME));
        sendUserToView(user);
    }

    @Override
    public void onSaveInstance(Bundle outState) {
        outState.putParcelable(ARG_USERNAME, Parcels.wrap(user));
    }

    private void toast(String errorString) {
        Toast.makeText(activity, errorString, Toast.LENGTH_SHORT).show();
    }


}
