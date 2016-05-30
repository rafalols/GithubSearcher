package eu.rafalolszewski.githubsearcher.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.dao.HistoryDao;
import eu.rafalolszewski.githubsearcher.ui.users_list.UserListActivity;

/**
 * Created by rafal on 02.05.16.
 */
public class SearchPresenter implements SearchVP.Presenter {

    private SearchActivity activity;
    private SearchVP.View searchView;
    private HistoryDao historyDao;

    public SearchPresenter(SearchActivity searchActivity, SearchVP.View searchView, HistoryDao historyDao) {
        this.activity = searchActivity;
        this.searchView = searchView;
        this.historyDao = historyDao;
    }

    @Override
    public void search(String searchString) {
        if (searchString.isEmpty()){
            Toast.makeText(activity, activity.getString(R.string.cant_search_empty), Toast.LENGTH_LONG).show();
        }else if (activity.getApi() == null){
            Toast.makeText(activity, activity.getString(R.string.wait_for_api), Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent(activity, UserListActivity.class);
            intent.putExtra(SEARCH_STRING, searchString);
            activity.startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        refreshHistory();
    }

    @Override
    public void onRestoreInstance(Bundle savedInstanceState) {
        refreshHistory();
    }

    private void refreshHistory() {
        searchView.refreshHistory(historyDao.getHistory());
    }

    @Override
    public void onSaveInstance(Bundle bundle) {

    }

}
