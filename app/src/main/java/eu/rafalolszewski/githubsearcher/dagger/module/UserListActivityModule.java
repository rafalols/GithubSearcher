package eu.rafalolszewski.githubsearcher.dagger.module;

import dagger.Module;
import dagger.Provides;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerUserListActivity;
import eu.rafalolszewski.githubsearcher.dao.HistoryDaoImpl;
import eu.rafalolszewski.githubsearcher.view.activity.UserListActivity;
import eu.rafalolszewski.githubsearcher.view.adapter.UserListAdapter;
import eu.rafalolszewski.githubsearcher.view.fragment.UserListView;
import eu.rafalolszewski.githubsearcher.view.presenter.UserListPresenter;
import eu.rafalolszewski.githubsearcher.view.presenter.UserListPresenterImpl;

/**
 * Created by rafal on 02.05.16.
 */
@Module
public class UserListActivityModule {

    private UserListActivity userListActivity;
    private UserListView userListView;

    public UserListActivityModule(UserListActivity userListActivity, UserListView userListView) {
        this.userListActivity = userListActivity;
        this.userListView = userListView;
    }

    @Provides
    @PerUserListActivity
    UserListPresenter providesUserListPresenter(HistoryDaoImpl historyDao){
        return new UserListPresenterImpl(userListActivity, userListView, historyDao);
    }

    @Provides
    @PerUserListActivity
    UserListAdapter providesUserListAdapter(UserListPresenter presenter){
        return new UserListAdapter(userListActivity, presenter);
    }

}
