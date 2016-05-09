package eu.rafalolszewski.githubsearcher.dao;

import eu.rafalolszewski.githubsearcher.model.SearchHistory;
import rx.Observable;

/**
 * Created by rafal on 05.05.16.
 */
public interface HistoryDao {

    public Observable<SearchHistory> getHistory();

    public void putSearchToHistory(String searchString, int numberOfResults);

}
