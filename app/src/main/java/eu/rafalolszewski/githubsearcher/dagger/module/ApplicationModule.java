package eu.rafalolszewski.githubsearcher.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.rafalolszewski.githubsearcher.GitHubSearcherApplication;

/**
 * Created by rafal on 02.05.16.
 */
@Module
public class ApplicationModule {

    GitHubSearcherApplication application;

    public ApplicationModule(GitHubSearcherApplication application){
        this.application = application;
    }

    @Singleton
    @Provides
    GitHubSearcherApplication providesApplication(){
        return application;
    }
}
