package eu.rafalolszewski.githubsearcher.ui.details;

import android.os.Build;
import android.os.Bundle;

import javax.inject.Inject;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.dagger.component.DaggerUserDetailsComponent;
import eu.rafalolszewski.githubsearcher.dagger.component.UserDetailsComponent;
import eu.rafalolszewski.githubsearcher.dagger.module.UserDetailsModule;
import eu.rafalolszewski.githubsearcher.ui.base.BaseActivity;

public class UserDetailsActivity extends BaseActivity {

    UserDetailsComponent component;

    @Inject
    UserDetailsVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        waitForLoadImage();

        UserDetailsFragment fragment = (UserDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.user_details_fragment);

        initComponent(fragment);
        component.inject(this);
        component.inject(fragment);

        presenter.onCreate(savedInstanceState);
    }

    private void initComponent(UserDetailsVP.View detailsView) {
        component = DaggerUserDetailsComponent.builder()
                .applicationComponent(getAppComponent())
                .userDetailsModule(new UserDetailsModule(this, detailsView))
                .build();
    }

    public void waitForLoadImage(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
        }
    }

    public void whenImageIsLoaded(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startPostponedEnterTransition();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        presenter.onSave(outState);
        super.onSaveInstanceState(outState);
    }
}
