package eu.rafalolszewski.githubsearcher.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import eu.rafalolszewski.githubsearcher.GitHubSearcherApplication;
import eu.rafalolszewski.githubsearcher.dagger.module.ApplicationModule;

/**
 * Created by rafal on 02.05.16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    GitHubSearcherApplication application();

}
