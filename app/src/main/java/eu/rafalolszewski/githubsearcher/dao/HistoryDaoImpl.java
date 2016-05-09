package eu.rafalolszewski.githubsearcher.dao;

import java.util.Date;
import java.util.List;

import eu.rafalolszewski.githubsearcher.model.SearchHistory;
import io.realm.Realm;
import io.realm.Sort;

/**
 * Created by rafal on 05.05.16.
 */
public class HistoryDaoImpl implements HistoryDao {

    private static final String ORDERBY_FIELD = "searchDate";

    private Realm realm;

    public HistoryDaoImpl(Realm realm) {
        this.realm = realm;
    }

    @Override
    public List<SearchHistory> getHistory() {
        return realm.where(SearchHistory.class).findAllSorted(ORDERBY_FIELD, Sort.DESCENDING);
    }

    @Override
    public void putSearchToHistory(String searchString, int numberOfResults) {
        SearchHistory search = new SearchHistory();
        search.setSearchString(searchString);
        search.setSearchDate(getCurrentDate());
        search.setNumberOfResults(numberOfResults);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(search);
        realm.commitTransaction();
    }

    private long getCurrentDate() {
        Date date = new Date();
        return date.getTime();
    }

}
