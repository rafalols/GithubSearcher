package eu.rafalolszewski.githubsearcher.view.activity;

import android.support.v7.app.AppCompatActivity;

import eu.rafalolszewski.githubsearcher.GitHubSearcherApplication;
import eu.rafalolszewski.githubsearcher.api.GitHubApi;

/**
 * Created by rafal on 02.05.16.
 */
public class BaseActivity extends AppCompatActivity{

    public GitHubApi getApi() {
        GitHubSearcherApplication app = (GitHubSearcherApplication) getApplication();
        return app.api;
    }

}