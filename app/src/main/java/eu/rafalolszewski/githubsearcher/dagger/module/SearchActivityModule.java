package eu.rafalolszewski.githubsearcher.dagger.module;

import dagger.Module;
import dagger.Provides;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerSearchActivity;
import eu.rafalolszewski.githubsearcher.dao.HistoryDaoImpl;
import eu.rafalolszewski.githubsearcher.view.activity.SearchActivity;
import eu.rafalolszewski.githubsearcher.view.adapter.HistoryAdapter;
import eu.rafalolszewski.githubsearcher.view.fragment.SearchView;
import eu.rafalolszewski.githubsearcher.view.presenter.SearchPresenter;
import eu.rafalolszewski.githubsearcher.view.presenter.SearchPresenterImpl;

/**
 * Created by rafal on 02.05.16.
 */
@Module
public class SearchActivityModule {

    private SearchActivity searchActivity;
    private SearchView searchView;

    public SearchActivityModule(SearchActivity searchActivity, SearchView searchView) {
        this.searchActivity = searchActivity;
        this.searchView = searchView;
    }

    @Provides
    @PerSearchActivity
    SearchPresenter providesSearchPresenter(HistoryDaoImpl historyDao){
        return new SearchPresenterImpl(searchActivity, searchView, historyDao);
    }

    @Provides
    @PerSearchActivity
    HistoryAdapter providesHistoryAdapter(){
        return new HistoryAdapter(searchActivity);
    }

}
