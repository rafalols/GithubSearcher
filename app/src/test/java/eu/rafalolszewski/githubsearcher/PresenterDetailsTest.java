package eu.rafalolszewski.githubsearcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import eu.rafalolszewski.githubsearcher.model.UserDetails;
import eu.rafalolszewski.githubsearcher.ui.details.UserDetailsActivity;
import eu.rafalolszewski.githubsearcher.ui.details.UserDetailsPresenter;
import eu.rafalolszewski.githubsearcher.ui.details.UserDetailsVP;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by rafal on 25.05.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PresenterDetailsTest {

    private final static String LOGIN_STRING = "login";

    UserDetailsVP.Presenter presenter;

    @Mock
    UserDetailsActivity activity;

    @Mock
    UserDetailsVP.View view;

    @Mock
    GitHubApi gitHubApi;

    UserDetails user;

    @Before
    public void setup(){

        setupUser();
        when(activity.getApi()).thenReturn(gitHubApi);
        when(gitHubApi.getUser(LOGIN_STRING)).thenReturn(Observable.just(user));
        presenter = new UserDetailsPresenter(activity, view, Schedulers.immediate());
    }

    private void setupUser() {
        user = new UserDetails();
        user.login = LOGIN_STRING;
    }

    @Test
    public void getUserByNameTest(){
        presenter.getUserByLogin(LOGIN_STRING);

        verify(gitHubApi).getUser(LOGIN_STRING);
        verify(view).onGetUser(user);
        verify(view).setProgressIndicator(false);
    }

    @Test
    public void onLoadedImageTest(){
        presenter.onLoadedImage(true);

        verify(activity).whenImageIsLoaded();
    }

}
