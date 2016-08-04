package eu.rafalolszewski.githubsearcher.ui.details;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.transition.Transition;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.dagger.component.DaggerUserDetailsComponent;
import eu.rafalolszewski.githubsearcher.dagger.component.UserDetailsComponent;
import eu.rafalolszewski.githubsearcher.dagger.module.UserDetailsModule;
import eu.rafalolszewski.githubsearcher.model.UserDetails;
import eu.rafalolszewski.githubsearcher.ui.base.BaseActivity;

public class UserDetailsActivity extends BaseActivity implements UserDetailsVP.View{

    UserDetailsComponent component;

    @Bind(R.id.image)
    ImageView avatar;

    @Bind(R.id.login)
    TextView loginTV;

    @Bind(R.id.name)
    TextView nameTV;

    @Bind(R.id.email)
    TextView emailTV;

    @Bind(R.id.followers)
    TextView followersTV;

    @Bind(R.id.repos)
    TextView reposTV;

    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.error)
    TextView errorTV;

    @Inject
    UserDetailsVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        ButterKnife.bind(this);

        waitForLoadImage();

        initComponent();
        component.inject(this);

        if (savedInstanceState != null){
            presenter.onRestoreInstance(savedInstanceState);
        }else {
            presenter.getUserByLogin(getIntent().getStringExtra(UserDetailsVP.Presenter.ARG_USERNAME));
        }

        setTransitionListener();
    }

    private void initComponent() {
        component = DaggerUserDetailsComponent.builder()
                .applicationComponent(getAppComponent())
                .userDetailsModule(new UserDetailsModule(this, this))
                .build();
    }

    private void waitForLoadImage(){
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
        presenter.onSaveInstance(outState);
        super.onSaveInstanceState(outState);
    }

    private void setTransitionListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {}
                @Override
                public void onTransitionEnd(Transition transition) {
                    presenter.onTransitionFinished();
                }
                @Override
                public void onTransitionCancel(Transition transition) {}
                @Override
                public void onTransitionPause(Transition transition) {}
                @Override
                public void onTransitionResume(Transition transition) {  }
            });
        }else {
            presenter.onTransitionFinished();
        }
    }

    @OnClick(R.id.fab)
    void clickFab(){
        presenter.openUserProfile();
    }

    @Override
    public void onGetUser(UserDetails user) {

        Picasso.with(this).load(user.avatarUrl).into(avatar, new Callback() {
            @Override
            public void onSuccess() {
                presenter.onLoadedImage(true);
            }

            @Override
            public void onError() {
                presenter.onLoadedImage(false);
            }
        });

        loginTV.setText(user.login);
        nameTV.setText(user.name);
        emailTV.setText(user.email);
        followersTV.setText(String.valueOf(user.followers));
        reposTV.setText(String.valueOf(user.publicRepos));

    }

    @Override
    public void setProgressIndicator(boolean enabled) {
        if (enabled){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void animateFabButton() {
        fab.setVisibility(View.VISIBLE);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.from_bottom);
        fab.startAnimation(anim);
    }

    @Override
    public void setLoadDataError(Throwable t) {
        errorTV.setVisibility(View.VISIBLE);
    }
}
