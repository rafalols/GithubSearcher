package eu.rafalolszewski.githubsearcher.ui.result;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.transition.TransitionManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.dagger.component.DaggerResultActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.component.ResultActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.module.ResultActivityModule;
import eu.rafalolszewski.githubsearcher.model.GithubRepoSearch;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import eu.rafalolszewski.githubsearcher.ui.base.BaseActivity;

public class ResultActivity extends BaseActivity implements ResultVP.View{

    private static final int PAGER_USERS = 0;
    private static final int PAGER_REPOS = 1;
    private static final String SEARCH_STRING = "searchString";


    ResultActivityComponent component;

    @Inject
    UserListFragment usersFragment;

    @Inject
    RepoListFragment repoFragment;

    @Inject
    ResultVP.Presenter presenter;

    @Bind(R.id.pager) ViewPager pager;

    @Bind(R.id.tabs) LinearLayout tabsContainer;
    @Bind(R.id.button_repos) ImageButton buttonRepos;
    @Bind(R.id.button_users) ImageButton buttonUsers;

    public String searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ButterKnife.bind(this);

        if (savedInstanceState != null){
            searchString = savedInstanceState.getString(SEARCH_STRING);
        }else {
            searchString = getIntent().getExtras().getString(SEARCH_STRING);
        }

        initComponent();

        pager.setAdapter(new ResultPagerAdapter(getSupportFragmentManager()));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(SEARCH_STRING, searchString);
        super.onSaveInstanceState(outState);
    }

    private void initComponent() {
        component = DaggerResultActivityComponent.builder()
                .applicationComponent(getAppComponent())
                .resultActivityModule(new ResultActivityModule(this, getApi()))
                .build();
        component.inject(this);
        component.inject(usersFragment);
    }

    @OnClick(R.id.button_users)
    public void clickUsers(){
//        if (pager.getCurrentItem() != PAGER_USERS){
            pager.setCurrentItem(PAGER_USERS);
            animateButtons(PAGER_USERS);
//        }
    }

    @OnClick(R.id.button_repos)
    public void clickRepos(){
//        if (pager.getCurrentItem() != PAGER_REPOS){
            pager.setCurrentItem(PAGER_USERS);
            animateButtons(PAGER_REPOS);
//        }
    }

    private void animateButtons(int pageId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(tabsContainer);
        }
        if (pageId == PAGER_USERS){
            buttonUsers.setImageResource(R.drawable.ic_person_blue_24dp);
            buttonUsers.setBackgroundColor(ContextCompat.getColor(this, R.color.activeTabBackground));
            buttonRepos.setImageResource(R.drawable.ic_folder_white_24dp);
            buttonRepos.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }else{
            buttonRepos.setImageResource(R.drawable.ic_folder_blue_24dp);
            buttonRepos.setBackgroundColor(ContextCompat.getColor(this, R.color.activeTabBackground));
            buttonUsers.setImageResource(R.drawable.ic_person_white_24dp);
            buttonUsers.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
    }


    //RESULT PRESENTER METHODS:

    @Override
    public void refreshUsers(GithubUsersSearch usersList) {
        usersFragment.refreshUsers(usersList);
    }

    @Override
    public void refreshRepos(GithubRepoSearch reposList) {

    }

    @Override
    public void setUsersProgressIndicator(boolean enabled) {
        usersFragment.setProgressIndicator(enabled);
    }

    @Override
    public void setReposProgressIndicator(boolean enabled) {

    }

    @Override
    public void setLoadUsersError(boolean status) {
        usersFragment.setLoadDataError(status);
    }

    @Override
    public void setLoadReposError(boolean status) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    private class ResultPagerAdapter extends FragmentPagerAdapter {

        public ResultPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return usersFragment;
                default:
                    // return repositoriesFragment
                    return usersFragment;
            }
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

}
