package eu.rafalolszewski.githubsearcher.view.activity;

import android.os.Bundle;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.dagger.component.DaggerSearchActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.component.SearchActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.module.SearchActivityModule;
import eu.rafalolszewski.githubsearcher.view.fragment.SearchFragment;
import eu.rafalolszewski.githubsearcher.view.fragment.SearchView;

public class SearchActivity extends BaseActivity {

    public SearchActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.search_fragment);

        initComponent(searchFragment);
        component.inject(searchFragment);
    }

    private void initComponent(SearchView searchView){
        component = DaggerSearchActivityComponent.builder()
                .searchActivityModule(new SearchActivityModule(this, searchView))
                .build();
    }

}
