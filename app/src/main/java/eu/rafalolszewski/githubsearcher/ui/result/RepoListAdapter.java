package eu.rafalolszewski.githubsearcher.ui.result;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.model.GithubRepoSearch;
import eu.rafalolszewski.githubsearcher.model.RepoPreview;

/**
 * Created by Rafał Olszewski (rafal.olszewski@hycom.pl) on 04.08.16.
 */
public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    GithubRepoSearch repoList;

    private final ResultVP.Presenter presenter;
    private Context context;

    private int lastPosition = -1;

    public RepoListAdapter(Context context, ResultVP.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }


    @Override
    public RepoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_row, parent, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(RepoListAdapter.ViewHolder holder, int position) {
        if (repoList != null) {
            RepoPreview repo = repoList.repos.get(position);
            holder.name.setText(repo.fullName);
            holder.desc.setText(repo.description);

            setAnimation(holder.itemView, position);
        }
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return repoList != null ? repoList.repos.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            desc = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
