package eu.rafalolszewski.githubsearcher.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.dagger.component.DaggerUserListActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.component.UserListActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.module.UserListActivityModule;
import eu.rafalolszewski.githubsearcher.view.fragment.UserListFragment;
import eu.rafalolszewski.githubsearcher.view.fragment.UserListView;

public class UserListActivity extends AppCompatActivity {

    public UserListActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        UserListFragment userListFragment = (UserListFragment) getSupportFragmentManager().findFragmentById(R.id.user_list_fragment);

        initComponent(userListFragment);
        component.inject(userListFragment);
    }

    private void initComponent(UserListView userListView) {
        component = DaggerUserListActivityComponent.builder()
                .userListActivityModule(new UserListActivityModule(this, userListView))
                .build();
    }

}
