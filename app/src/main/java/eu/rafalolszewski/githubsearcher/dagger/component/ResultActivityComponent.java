package eu.rafalolszewski.githubsearcher.dagger.component;

import dagger.Component;
import eu.rafalolszewski.githubsearcher.dagger.module.ResultActivityModule;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerResultActivity;
import eu.rafalolszewski.githubsearcher.ui.result.ResultActivity;
import eu.rafalolszewski.githubsearcher.ui.result.UserListAdapter;
import eu.rafalolszewski.githubsearcher.ui.result.UserListFragment;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListVP;

/**
 * Created by rafal on 02.05.16.
 */
@PerResultActivity
@Component(dependencies = ApplicationComponent.class, modules = ResultActivityModule.class)
public interface ResultActivityComponent {

    UserListVP.Presenter userListPresenter();

    UserListAdapter userListAdapter();

    void inject(ResultActivity resultActivity);
    void inject(UserListFragment userListFragment);

}
