package eu.rafalolszewski.githubsearcher.ui.search;

import eu.rafalolszewski.githubsearcher.model.SearchHistory;
import eu.rafalolszewski.githubsearcher.ui.base.BasePresenter;
import rx.Observable;

/**
 * Created by rafal on 23.05.16.
 */

public interface SearchVP {

    interface Presenter extends BasePresenter {

        public static final String SEARCH_STRING= "searchString";

        void search(String search);

        void onResume();

    }

    interface View {

        void refreshHistory(Observable<SearchHistory> searchHistory);

        void setupAdapter();

    }

}
