package eu.rafalolszewski.githubsearcher.ui.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.model.SearchHistory;
import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchVP.View {

    @Bind(R.id.edittext_searcher)
    EditText searchText;

    @Bind(R.id.list_search_history)
    ListView historyListView;

    @OnClick(R.id.button_search)
    public void search(){
        presenter.search(searchText.getText().toString());
    }

    @Inject
    SearchVP.Presenter presenter;

    @Inject
    HistoryAdapter historyAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ButterKnife.bind(this, view);

        return view;
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

    @Override
    public void onInjectDependencies() {
        historyListView.setAdapter(historyAdapter);
    }
}
