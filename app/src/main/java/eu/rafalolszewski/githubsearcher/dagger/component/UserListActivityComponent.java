package eu.rafalolszewski.githubsearcher.dagger.component;

import dagger.Component;
import eu.rafalolszewski.githubsearcher.dagger.module.UserListActivityModule;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerUserListActivity;
import eu.rafalolszewski.githubsearcher.view.activity.UserListActivity;
import eu.rafalolszewski.githubsearcher.view.adapter.UserListAdapter;
import eu.rafalolszewski.githubsearcher.view.fragment.UserListFragment;
import eu.rafalolszewski.githubsearcher.view.presenter.UserListPresenter;

/**
 * Created by rafal on 02.05.16.
 */
@PerUserListActivity
@Component(dependencies = ApplicationComponent.class, modules = UserListActivityModule.class)
public interface UserListActivityComponent {

    UserListPresenter userListPresenter();

    UserListAdapter userListAdapter();

    void inject(UserListFragment userListFragment);

    void inject(UserListActivity userListActivity);

}
