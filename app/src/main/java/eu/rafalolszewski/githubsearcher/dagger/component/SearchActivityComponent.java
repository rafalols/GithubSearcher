package eu.rafalolszewski.githubsearcher.dagger.component;

import dagger.Component;
import eu.rafalolszewski.githubsearcher.dagger.module.SearchActivityModule;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerSearchActivity;
import eu.rafalolszewski.githubsearcher.view.activity.SearchActivity;
import eu.rafalolszewski.githubsearcher.view.adapter.HistoryAdapter;
import eu.rafalolszewski.githubsearcher.view.fragment.SearchFragment;
import eu.rafalolszewski.githubsearcher.view.presenter.SearchPresenter;

/**
 * Created by rafal on 02.05.16.
 */
@PerSearchActivity
@Component(dependencies = ApplicationComponent.class, modules = SearchActivityModule.class)
public interface SearchActivityComponent {

    SearchPresenter searchPresenter();

    HistoryAdapter historyAdapter();

    void inject(SearchFragment searchFragment);

    void inject(SearchActivity searchActivity);

}