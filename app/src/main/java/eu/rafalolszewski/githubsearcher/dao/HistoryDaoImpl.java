package eu.rafalolszewski.githubsearcher.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.rafalolszewski.githubsearcher.model.SearchHistory;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;

/**
 * Created by rafal on 05.05.16.
 */
public class HistoryDaoImpl implements HistoryDao {

    private static final String ORDERBY_FIELD = "searchDate";
    private static final int HISTORY_SIZE = 500;

    private Realm realm;

    public HistoryDaoImpl(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Observable< List<SearchHistory>> getHistory() {
        return realm.where(SearchHistory.class).findAllSorted(ORDERBY_FIELD, Sort.DESCENDING)
                .asObservable()
                .flatMap(history -> historyToList(history));
    }

    private Observable< List<SearchHistory>> historyToList(RealmResults<SearchHistory> histories) {
        List<SearchHistory> list = new ArrayList<>();
        list.addAll(histories);
        return Observable.just(list);
    }

    @Override
    public void putSearchToHistory(String searchString, int usersCount, int reposCount) {
        SearchHistory search = new SearchHistory();
        search.setSearchString(searchString);
        search.setSearchDate(getCurrentDate());
        search.setNumberOfUsers(usersCount);
        search.setNumberOfRepos(reposCount);
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
