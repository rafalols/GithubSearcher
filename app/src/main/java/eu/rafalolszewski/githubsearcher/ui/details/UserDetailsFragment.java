package eu.rafalolszewski.githubsearcher.ui.details;


import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import eu.rafalolszewski.githubsearcher.model.GithubUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserDetailsFragment extends Fragment implements UserDetailsVP.View{

    private static final String TAG = "UserDetailsFragment";

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



    public UserDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    @MainThread
    public void onGetUser(GithubUser user) {

        Picasso.with(getActivity()).load(user.avatarUrl).into(avatar, new Callback() {
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
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.from_bottom);
        fab.startAnimation(anim);
    }

    @Override
    public void setLoadDataError(Throwable t) {
        errorTV.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.fab)
    public void clickFab(){
        presenter.openUserProfile();
    }

}
