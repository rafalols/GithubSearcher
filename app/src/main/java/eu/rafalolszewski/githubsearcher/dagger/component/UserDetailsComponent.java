package eu.rafalolszewski.githubsearcher.dagger.component;

import dagger.Component;
import eu.rafalolszewski.githubsearcher.dagger.module.UserDetailsModule;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerUserDetailsActivity;
import eu.rafalolszewski.githubsearcher.ui.details.UserDetailsActivity;
import eu.rafalolszewski.githubsearcher.ui.details.UserDetailsVP;

/**
 * Created by rafal on 23.05.16.
 */
@PerUserDetailsActivity
@Component(dependencies = ApplicationComponent.class, modules = UserDetailsModule.class)
public interface UserDetailsComponent {

    UserDetailsVP.Presenter userDetailsPresenter();

    void inject(UserDetailsActivity userDetailsActivity);
}
