package eu.rafalolszewski.githubsearcher.dagger.module;

import dagger.Module;
import dagger.Provides;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerUserDetailsActivity;
import eu.rafalolszewski.githubsearcher.ui.details.UserDetailsActivity;
import eu.rafalolszewski.githubsearcher.ui.details.UserDetailsPresenter;
import eu.rafalolszewski.githubsearcher.ui.details.UserDetailsVP;

/**
 * Created by rafal on 23.05.16.
 */
@Module
public class UserDetailsModule {

    private UserDetailsActivity activity;
    private UserDetailsVP.View view;

    public UserDetailsModule(UserDetailsActivity activity, UserDetailsVP.View view) {
        this.activity = activity;
        this.view = view;
    }

    @Provides
    @PerUserDetailsActivity
    UserDetailsVP.Presenter providesUserDetailsPresenter(){
        return new UserDetailsPresenter(activity, view);
    }

}
