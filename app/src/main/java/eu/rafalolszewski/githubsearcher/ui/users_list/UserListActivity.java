package eu.rafalolszewski.githubsearcher.ui.users_list;

import android.os.Bundle;

import javax.inject.Inject;

import eu.rafalolszewski.githubsearcher.GitHubSearcherApplication;
import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.dagger.component.DaggerUserListActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.component.UserListActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.module.UserListActivityModule;
import eu.rafalolszewski.githubsearcher.ui.base.BaseActivity;

public class UserListActivity extends BaseActivity {

    public UserListActivityComponent component;
    private GitHubSearcherApplication app;

    @Inject
    public UserListVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        UserListFragment userListFragment = (UserListFragment) getSupportFragmentManager().findFragmentById(R.id.user_list_fragment);

        initComponent(userListFragment);

        component.inject(userListFragment);
        userListFragment.setupRecyclerView();

        component.inject(this);

        presenter.onCreate(savedInstanceState);
    }

    private void initComponent(UserListVP.View userListView) {
        component = DaggerUserListActivityComponent.builder()
                .applicationComponent(getAppComponent())
                .userListActivityModule(new UserListActivityModule(this, userListView))
                .build();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
