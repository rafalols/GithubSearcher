package eu.rafalolszewski.githubsearcher.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.model.SearchHistory;

/**
 * Created by rafal on 05.05.16.
 */
public class HistoryAdapter extends BaseAdapter {

    private List<SearchHistory> searchHistoryList = new ArrayList<>();
    private Context context;
    private SearchVP.Presenter presenter;

    public HistoryAdapter(Context context, SearchVP.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public int getCount() {
        return searchHistoryList != null ? searchHistoryList.size() : 0;
    }

    @Override
    public SearchHistory getItem(int position) {
        return searchHistoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_row, parent, false);

        TextView search = (TextView) view.findViewById(R.id.search);
        TextView results = (TextView) view.findViewById(R.id.results);

        SearchHistory item = searchHistoryList.get(position);

        search.setText(item.searchString);
        results.setText(context.getString(R.string.number_of_results) + item.numberOfResults);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.search(item.searchString);
            }
        });

        return view;
    }

    public List<SearchHistory> getSearchHistoryList() {
        return searchHistoryList;
    }
}
