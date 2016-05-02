package eu.rafalolszewski.githubsearcher.dagger.component;

import dagger.Component;
import eu.rafalolszewski.githubsearcher.dagger.module.UserListActivityModule;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerUserListActivity;
import eu.rafalolszewski.githubsearcher.view.fragment.UserListFragment;
import eu.rafalolszewski.githubsearcher.view.presenter.UserListPresenter;

/**
 * Created by rafal on 02.05.16.
 */
@PerUserListActivity
@Component(modules = UserListActivityModule.class)
public interface UserListActivityComponent {

    UserListPresenter userListPresenter();

    void inject(UserListFragment userListFragment);

}
