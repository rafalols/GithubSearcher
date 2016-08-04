package eu.rafalolszewski.githubsearcher.ui.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.model.SearchHistory;

/**
 * Created by rafal on 05.05.16.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<SearchHistory> searchHistoryList = new ArrayList<>();
    private Context context;
    private SearchVP.Presenter presenter;

    public HistoryAdapter(Context context, SearchVP.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_row, parent, false);
        return new ViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchHistory item = searchHistoryList.get(position);
        holder.search.setText(item.searchString);
        holder.results.setText(context.getString(R.string.number_of_results) + item.numberOfUsers);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.search(item.searchString);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return searchHistoryList.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        if (searchHistoryList != null){
            if (searchHistoryList.size() <= 10){
                return searchHistoryList.size();
            }else {
                return 10;
            }
        }else {
            return 0;
        }
    }

    public void setHistoryList(List<SearchHistory> historyList) {
        this.searchHistoryList = historyList;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView search;
        TextView results;

        public ViewHolder(View itemView) {
            super(itemView);
            search = (TextView) itemView.findViewById(R.id.search);
            results = (TextView) itemView.findViewById(R.id.results);
        }
    }
}
