package eu.rafalolszewski.githubsearcher.dagger.component;

import dagger.Component;
import eu.rafalolszewski.githubsearcher.dagger.module.UserListActivityModule;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerUserListActivity;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListActivity;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListAdapter;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListFragment;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListVP;

/**
 * Created by rafal on 02.05.16.
 */
@PerUserListActivity
@Component(dependencies = ApplicationComponent.class, modules = UserListActivityModule.class)
public interface UserListActivityComponent {

    UserListVP.Presenter userListPresenter();

    UserListAdapter userListAdapter();

    void inject(UserListFragment userListFragment);

    void inject(UserListActivity userListActivity);

}
