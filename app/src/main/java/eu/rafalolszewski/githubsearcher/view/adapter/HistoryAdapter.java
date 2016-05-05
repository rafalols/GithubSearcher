package eu.rafalolszewski.githubsearcher.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.model.SearchHistory;

/**
 * Created by rafal on 05.05.16.
 */
public class HistoryAdapter extends BaseAdapter {

    private List<SearchHistory> searchHistoryList;
    private Context context;

    public HistoryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return searchHistoryList != null ? searchHistoryList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
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

        return view;
    }

    public void setSearchHistoryList(List<SearchHistory> searchHistoryList) {
        this.searchHistoryList = searchHistoryList;
    }
}