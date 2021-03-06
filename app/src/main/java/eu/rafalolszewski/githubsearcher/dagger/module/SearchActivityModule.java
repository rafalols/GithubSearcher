package eu.rafalolszewski.githubsearcher.dagger.module;

import dagger.Module;
import dagger.Provides;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerSearchActivity;
import eu.rafalolszewski.githubsearcher.dao.HistoryDaoImpl;
import eu.rafalolszewski.githubsearcher.ui.search.SearchActivity;
import eu.rafalolszewski.githubsearcher.ui.search.HistoryAdapter;
import eu.rafalolszewski.githubsearcher.ui.search.SearchPresenter;
import eu.rafalolszewski.githubsearcher.ui.search.SearchVP;

/**
 * Created by rafal on 02.05.16.
 */
@Module
public class SearchActivityModule {

    private SearchActivity searchActivity;
    private SearchVP.View searchView;

    public SearchActivityModule(SearchActivity searchActivity, SearchVP.View searchView) {
        this.searchActivity = searchActivity;
        this.searchView = searchView;
    }

    @Provides
    @PerSearchActivity
    SearchVP.Presenter providesSearchPresenter(HistoryDaoImpl historyDao){
        return new SearchPresenter(searchActivity, searchView, historyDao);
    }

    @Provides
    @PerSearchActivity
    HistoryAdapter providesHistoryAdapter(SearchVP.Presenter presenter){
        return new HistoryAdapter(searchActivity, presenter);
    }

}
