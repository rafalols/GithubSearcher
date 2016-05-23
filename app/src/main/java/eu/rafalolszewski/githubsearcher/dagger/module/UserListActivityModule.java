package eu.rafalolszewski.githubsearcher.dagger.module;

import dagger.Module;
import dagger.Provides;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerUserListActivity;
import eu.rafalolszewski.githubsearcher.dao.HistoryDaoImpl;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListActivity;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListAdapter;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListPresenter;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListVP;

/**
 * Created by rafal on 02.05.16.
 */
@Module
public class UserListActivityModule {

    private UserListActivity userListActivity;
    private UserListVP.View userListView;

    public UserListActivityModule(UserListActivity userListActivity, UserListVP.View userListView) {
        this.userListActivity = userListActivity;
        this.userListView = userListView;
    }

    @Provides
    @PerUserListActivity
    UserListVP.Presenter providesUserListPresenter(HistoryDaoImpl historyDao){
        return new UserListPresenter(userListActivity, userListView, historyDao);
    }

    @Provides
    @PerUserListActivity
    UserListAdapter providesUserListAdapter(UserListVP.Presenter presenter){
        return new UserListAdapter(userListActivity, presenter);
    }

}
