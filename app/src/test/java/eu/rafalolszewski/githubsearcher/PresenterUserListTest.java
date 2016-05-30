package eu.rafalolszewski.githubsearcher;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import eu.rafalolszewski.githubsearcher.dao.HistoryDao;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListActivity;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListPresenter;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListVP;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by rafal on 25.05.16.
 */

@RunWith(MockitoJUnitRunner.class)
public class PresenterUserListTest {

    private static final String SEARCH_STRING = "searchString";

    UserListVP.Presenter presenter;

    @Mock
    UserListActivity activity;

    @Mock
    UserListVP.View view;

    @Mock
    GitHubApi gitHubApi;

    @Mock
    HistoryDao historyDao;

    @Mock
    Observable<GithubUsersSearch> userListObservable;

    GithubUsersSearch userList;

    @Mock
    View avatarView, loginView;

    @Before
    public void setup(){
        setupSearch();
        when(activity.getApi()).thenReturn(gitHubApi);
        when(gitHubApi.searchForUsers(SEARCH_STRING)).thenReturn(Observable.just(userList));
        presenter = new UserListPresenter(activity, view, historyDao, Schedulers.immediate(), null);
    }

    private void setupSearch() {
        userList = new GithubUsersSearch();
        userList.count = 10;
    }

    @Test
    public void getUserListTest(){
        presenter.getUserList(SEARCH_STRING);

        verify(view).setProgressIndicator(true);
        verify(gitHubApi).searchForUsers(SEARCH_STRING);
        verify(view).onGetUsersList(userList);
        verify(view).setProgressIndicator(false);
    }


}
