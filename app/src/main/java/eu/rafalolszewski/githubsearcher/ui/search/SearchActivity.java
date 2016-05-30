package eu.rafalolszewski.githubsearcher.ui.search;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.dagger.component.DaggerSearchActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.component.SearchActivityComponent;
import eu.rafalolszewski.githubsearcher.dagger.module.SearchActivityModule;
import eu.rafalolszewski.githubsearcher.model.SearchHistory;
import eu.rafalolszewski.githubsearcher.ui.base.BaseActivity;
import rx.Observable;

public class SearchActivity extends BaseActivity implements SearchVP.View{

    public SearchActivityComponent component;

    @Bind(R.id.edittext_searcher)
    EditText searchText;

    @Bind(R.id.list_search_history)
    ListView historyListView;

    @OnClick(R.id.button_search)
    public void search(){
        presenter.search(searchText.getText().toString());
    }

    @Inject
    HistoryAdapter historyAdapter;

    @Inject
    public SearchVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        setOnSearchEnterListener();

        initComponent();

        component.inject(this);

        historyListView.setAdapter(historyAdapter);
    }

    private void initComponent(){
        component = DaggerSearchActivityComponent.builder()
                .applicationComponent(getAppComponent())
                .searchActivityModule(new SearchActivityModule(this, this))
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void refreshHistory(Observable<SearchHistory> searchHistory) {
        historyAdapter.getSearchHistoryList().clear();
        searchHistory
                .subscribe(history -> refreshHistoryAdapter(history));
    }

    private void refreshHistoryAdapter(SearchHistory history){
        historyAdapter.getSearchHistoryList().add(history);
        historyAdapter.notifyDataSetChanged();
    }

    private void setOnSearchEnterListener() {
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    presenter.search(searchText.getText().toString());
                    handled = true;
                }
                return handled;
            }
        });
    }

}
