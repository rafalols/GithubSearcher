package eu.rafalolszewski.githubsearcher.view.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.dao.HistoryDao;
import eu.rafalolszewski.githubsearcher.view.activity.SearchActivity;
import eu.rafalolszewski.githubsearcher.view.activity.UserListActivity;
import eu.rafalolszewski.githubsearcher.view.fragment.BaseView;
import eu.rafalolszewski.githubsearcher.view.fragment.SearchView;

/**
 * Created by rafal on 02.05.16.
 */
public class SearchPresenterImpl implements SearchPresenter {

    SearchActivity activity;
    SearchView searchView;
    HistoryDao historyDao;

    public SearchPresenterImpl(SearchActivity searchActivity, SearchView searchView, HistoryDao historyDao) {
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
    public void onCreate(Bundle savedInstanceState) {
        refreshHistory();
    }

    private void refreshHistory() {
        searchView.refreshHistory(historyDao.getHistory());
    }

    @Override
    public void onSave(Bundle bundle) {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void setView(BaseView view) {
        searchView = (SearchView) view;
    }
}
