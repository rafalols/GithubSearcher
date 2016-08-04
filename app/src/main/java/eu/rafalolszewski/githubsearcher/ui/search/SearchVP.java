package eu.rafalolszewski.githubsearcher.ui.search;

import java.util.List;

import eu.rafalolszewski.githubsearcher.model.SearchHistory;
import eu.rafalolszewski.githubsearcher.ui.base.BasePresenter;

/**
 * Created by rafal on 23.05.16.
 */

public interface SearchVP {

    interface Presenter extends BasePresenter {

        void search(String search);

        void loadHistory();

    }

    interface View {

        void refreshHistory(List<SearchHistory> searchHistory);

        void goToResultActivity(String search);
    }

}
