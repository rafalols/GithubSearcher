package eu.rafalolszewski.githubsearcher.dagger.module;

import android.support.test.espresso.idling.CountingIdlingResource;

import dagger.Module;
import dagger.Provides;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerUserListActivity;
import eu.rafalolszewski.githubsearcher.dao.HistoryDaoImpl;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListActivity;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListAdapter;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListPresenter;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListVP;
import rx.android.schedulers.AndroidSchedulers;

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
    UserListVP.Presenter providesUserListPresenter(HistoryDaoImpl historyDao, CountingIdlingResource idlingResource){
        return new UserListPresenter(userListActivity, userListView, historyDao, AndroidSchedulers.mainThread(), idlingResource);
    }

    @Provides
    @PerUserListActivity
    UserListAdapter providesUserListAdapter(UserListVP.Presenter presenter){
        return new UserListAdapter(userListActivity, presenter);
    }

}
