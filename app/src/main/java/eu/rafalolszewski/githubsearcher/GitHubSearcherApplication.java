package eu.rafalolszewski.githubsearcher;

import android.app.Application;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

import java.util.concurrent.Executors;

import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import eu.rafalolszewski.githubsearcher.dagger.component.ApplicationComponent;
import eu.rafalolszewski.githubsearcher.dagger.component.ApplicationProductionComponent;
import eu.rafalolszewski.githubsearcher.dagger.component.DaggerApplicationComponent;
import eu.rafalolszewski.githubsearcher.dagger.component.DaggerApplicationProductionComponent;
import eu.rafalolszewski.githubsearcher.dagger.module.ApiModule;
import eu.rafalolszewski.githubsearcher.dagger.module.ApplicationModule;

/**
 * Created by rafal on 02.05.16.
 */
public class GitHubSearcherApplication extends Application {


    public ApplicationComponent appComponent;
    public ApplicationProductionComponent appProductionComponent;
    public GitHubApi api;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponents();

        initApi();
    }

    private void initComponents(){
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        appProductionComponent = DaggerApplicationProductionComponent.builder()
                .executor(Executors.newSingleThreadExecutor())
                .applicationComponent(appComponent)
                .apiModule(new ApiModule())
                .build();
    }


    public void initApi() {
        Futures.addCallback(appProductionComponent.gitHubApi(),
                new FutureCallback<GitHubApi>() {
                    @Override
                    public void onSuccess(GitHubApi result) {
                        GitHubSearcherApplication.this.api = result;
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
    }

}
