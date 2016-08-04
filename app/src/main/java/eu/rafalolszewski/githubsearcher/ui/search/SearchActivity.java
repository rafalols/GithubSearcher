package eu.rafalolszewski.githubsearcher.ui.search;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

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
import eu.rafalolszewski.githubsearcher.ui.result.ResultActivity;

public class SearchActivity extends BaseActivity implements SearchVP.View{

    private static final String TAG = "SearchActivity";

    public static final String SEARCH_STRING= "searchString";

    public SearchActivityComponent component;

    @Bind(R.id.edittext_searcher)
    EditText searchText;

    @Bind(R.id.list_search_history)
    RecyclerView historyListView;

    @Bind(R.id.button_search)
    ImageButton searchButton;

    @OnClick(R.id.button_search)
    public void search(){
        presenter.search(searchText.getText().toString());
    }

    @Inject
    HistoryAdapter historyAdapter;

    @Inject
    public SearchVP.Presenter presenter;

    @Inject
    RecyclerView.ItemAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        setOnSearchEnterListener();

        initComponent();

        component.inject(this);

        historyListView.setLayoutManager(new LinearLayoutManager(this));
        historyListView.setAdapter(historyAdapter);
        historyListView.setItemAnimator(animator);
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
        presenter.loadHistory();
    }

    @Override
    public void refreshHistory(List<SearchHistory> searchHistory) {
        historyAdapter.setHistoryList(searchHistory);
        historyAdapter.notifyDataSetChanged();
    }

    @Override
    public void goToResultActivity(String search) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(SEARCH_STRING, search);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }else {
            startActivity(intent);
        }
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
