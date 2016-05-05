package eu.rafalolszewski.githubsearcher.dao;

import java.util.List;

import eu.rafalolszewski.githubsearcher.model.SearchHistory;

/**
 * Created by rafal on 05.05.16.
 */
public interface HistoryDao {

    public List<SearchHistory> getHistory();

    public void putSearchToHistory(String searchString, int numberOfResults);

}
