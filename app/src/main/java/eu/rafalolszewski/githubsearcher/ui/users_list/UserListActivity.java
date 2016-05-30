package eu.rafalolszewski.githubsearcher.ui.users_list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.rafalolszewski.githubsearcher.GitHubSearcherApplication;
import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.dagger.component.DaggerUserListActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.component.UserListActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.module.UserListActivityModule;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import eu.rafalolszewski.githubsearcher.ui.base.BaseActivity;
import eu.rafalolszewski.githubsearcher.ui.search.SearchPresenter;

public class UserListActivity extends BaseActivity  implements UserListVP.View{

    public UserListActivityComponent component;
    private GitHubSearcherApplication app;

    @Bind(R.id.label_users)
    TextView usersLabel;

    @Bind(R.id.recycler_user_list)
    RecyclerView recyclerView;

    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    @Bind(R.id.error)
    TextView errorTextView;

    RecyclerView.LayoutManager rwLayoutManager;

    @Inject
    UserListAdapter rwAdapter;

    @Inject
    UserListVP.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ButterKnife.bind(this);

        initComponent();

        component.inject(this);
        setupRecyclerView();

        if (savedInstanceState != null && savedInstanceState.containsKey(UserListPresenter.SEARCH_RESULT)){
            presenter.onRestoreInstance(savedInstanceState);
        }else {
            presenter.getUserList(getIntent().getStringExtra(SearchPresenter.SEARCH_STRING));
        }

    }

    private void initComponent() {
        component = DaggerUserListActivityComponent.builder()
                .applicationComponent(getAppComponent())
                .userListActivityModule(new UserListActivityModule(this, this))
                .build();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onGetUsersList(GithubUsersSearch usersList) {
        usersLabel.setText(getString(R.string.Users) + " found " + usersList.count);
        synchronized (rwAdapter) {
            rwAdapter.setUsersList(usersList);
            rwAdapter.notifyDataSetChanged();
        }
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
    public void setLoadDataError(Throwable t) {
        errorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setupRecyclerView() {
        rwLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rwLayoutManager);
        recyclerView.setAdapter(rwAdapter);
    }
}
