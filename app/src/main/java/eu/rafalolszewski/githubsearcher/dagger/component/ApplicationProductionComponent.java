package eu.rafalolszewski.githubsearcher.dagger.component;

import com.google.common.util.concurrent.ListenableFuture;

import dagger.producers.ProductionComponent;
import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import eu.rafalolszewski.githubsearcher.dagger.module.ApiModule;

/**
 * Created by rafal on 02.05.16.
 */
@ProductionComponent(dependencies = ApplicationComponent.class, modules = ApiModule.class)
public interface ApplicationProductionComponent {

    ListenableFuture<GitHubApi> gitHubApi();

}
