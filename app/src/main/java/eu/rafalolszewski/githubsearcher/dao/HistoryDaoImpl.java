package eu.rafalolszewski.githubsearcher.dao;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.query.Select;

import java.util.Date;
import java.util.List;

import eu.rafalolszewski.githubsearcher.model.SearchHistory;

/**
 * Created by rafal on 05.05.16.
 */
public class HistoryDaoImpl implements HistoryDao {

    private static final String ORDER_BY = "search_date DESC";

    public HistoryDaoImpl(Context context) {
        SugarContext.init(context);
    }

    @Override
    public List<SearchHistory> getHistory() {
        return Select.from(SearchHistory.class)
                .orderBy(ORDER_BY)
                .list();
    }

    @Override
    public void putSearchToHistory(String searchString, int numberOfResults) {
        String where = "searchString = " + searchString;
        SearchHistory searchHistory = SearchHistory.find(SearchHistory.class, where).get(0);
        if (searchHistory != null){
            searchHistory.searchDate = getDate();
            searchHistory.numberOfResults = numberOfResults;
            searchHistory.save();
        }else {
            searchHistory = new SearchHistory(searchString, getDate(), numberOfResults);
            searchHistory.save();
        }
    }

    private long getDate() {
        Date date = new Date();
        return date.getTime();
    }


}
