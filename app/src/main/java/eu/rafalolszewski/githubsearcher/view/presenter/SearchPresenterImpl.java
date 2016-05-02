package eu.rafalolszewski.githubsearcher.view.presenter;

import android.os.Bundle;

import eu.rafalolszewski.githubsearcher.view.activity.SearchActivity;
import eu.rafalolszewski.githubsearcher.view.fragment.SearchView;

/**
 * Created by rafal on 02.05.16.
 */
public class SearchPresenterImpl implements SearchPresenter {

    SearchActivity searchActivity;
    SearchView searchView;

    public SearchPresenterImpl(SearchActivity searchActivity, SearchView searchView) {
        this.searchActivity = searchActivity;
        this.searchView = searchView;
    }

    @Override
    public void search(String search) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onSave(Bundle bundle) {

    }

    @Override
    public void onStop() {

    }
}
