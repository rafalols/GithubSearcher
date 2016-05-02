package eu.rafalolszewski.githubsearcher.view.fragment;

import java.util.List;

import eu.rafalolszewski.githubsearcher.model.SearchHistory;

/**
 * Created by rafal on 02.05.16.
 */
public interface SearchView {

    public void refreshHistory(List<SearchHistory> searchHistory);

}
