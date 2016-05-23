package eu.rafalolszewski.githubsearcher.ui.base;

import android.support.v7.app.AppCompatActivity;

import eu.rafalolszewski.githubsearcher.GitHubSearcherApplication;
import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import eu.rafalolszewski.githubsearcher.dagger.component.ApplicationComponent;

/**
 * Created by rafal on 02.05.16.
 */
public class BaseActivity extends AppCompatActivity{

    public GitHubApi getApi() {
        GitHubSearcherApplication app = (GitHubSearcherApplication) getApplication();
        return app.api;
    }

    public ApplicationComponent getAppComponent(){
        GitHubSearcherApplication app = (GitHubSearcherApplication) getApplication();
        return app.appComponent;
    }

}
