package eu.rafalolszewski.githubsearcher.ui.search;

import android.os.Bundle;

import javax.inject.Inject;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.dagger.component.DaggerSearchActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.component.SearchActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.module.SearchActivityModule;
import eu.rafalolszewski.githubsearcher.ui.base.BaseActivity;

public class SearchActivity extends BaseActivity {

    public SearchActivityComponent component;

    @Inject
    public SearchVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.search_fragment);

        initComponent(searchFragment);

        component.inject(this);

        component.inject(searchFragment);
        searchFragment.setupAdapter();
    }

    private void initComponent(SearchVP.View searchView){
        component = DaggerSearchActivityComponent.builder()
                .applicationComponent(getAppComponent())
                .searchActivityModule(new SearchActivityModule(this, searchView))
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }
}
