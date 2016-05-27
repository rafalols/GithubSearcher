package eu.rafalolszewski.githubsearcher.dao;

import java.util.Date;

import eu.rafalolszewski.githubsearcher.model.SearchHistory;
import io.realm.Realm;
import io.realm.Sort;
import rx.Observable;

/**
 * Created by rafal on 05.05.16.
 */
public class HistoryDaoImpl implements HistoryDao {

    private static final String ORDERBY_FIELD = "searchDate";
    private static final int HISTORY_SIZE = 10;

    private Realm realm;

    public HistoryDaoImpl(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Observable<SearchHistory> getHistory() {
        return realm.where(SearchHistory.class).findAllSorted(ORDERBY_FIELD, Sort.DESCENDING)
                .asObservable()
                .flatMap(histories -> Observable.from(histories))
                .take(HISTORY_SIZE);
    }

    @Override
    public void putSearchToHistory(String searchString, int numberOfResults) {
        SearchHistory search = new SearchHistory();
        search.setSearchString(searchString);
        search.setSearchDate(getCurrentDate());
        search.setNumberOfResults(numberOfResults);
        copyOrUpdateToRealm(search);
    }

    private void copyOrUpdateToRealm(SearchHistory search) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(search);
        realm.commitTransaction();
    }

    private long getCurrentDate() {
        Date date = new Date();
        return date.getTime();
    }

}
