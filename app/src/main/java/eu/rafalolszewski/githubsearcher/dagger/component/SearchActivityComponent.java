package eu.rafalolszewski.githubsearcher.dagger.component;

import dagger.Component;
import eu.rafalolszewski.githubsearcher.dagger.module.SearchActivityModule;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerSearchActivity;
import eu.rafalolszewski.githubsearcher.ui.search.HistoryAdapter;
import eu.rafalolszewski.githubsearcher.ui.search.SearchActivity;
import eu.rafalolszewski.githubsearcher.ui.search.SearchVP;

/**
 * Created by rafal on 02.05.16.
 */
@PerSearchActivity
@Component(dependencies = ApplicationComponent.class, modules = SearchActivityModule.class)
public interface SearchActivityComponent {

    SearchVP.Presenter searchPresenter();

    HistoryAdapter historyAdapter();

    void inject(SearchActivity searchActivity);

}