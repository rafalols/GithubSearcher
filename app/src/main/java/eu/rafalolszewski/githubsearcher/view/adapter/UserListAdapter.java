package eu.rafalolszewski.githubsearcher.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import eu.rafalolszewski.githubsearcher.model.UserPreview;
import eu.rafalolszewski.githubsearcher.view.presenter.UserListPresenter;

/**
 * Created by Rafał Olszewski on 02.05.16.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private GithubUsersSearch usersList;
    private Context context;
    private UserListPresenter presenter;

    public UserListAdapter(Context context, UserListPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (usersList != null) {
            UserPreview user = usersList.usersPreviews.get(position);
            Picasso.with(context).load(user.avatarUrl).into(holder.avatar);
            holder.name.setText(user.login);
            holder.avatar.setOnClickListener(new OnAvatarClickListener(user.login));
        }
    }

    @Override
    public int getItemCount() {
        return usersList != null ? usersList.usersPreviews.size() : 0;
    }

    public void setUsersList(GithubUsersSearch usersList) {
        this.usersList = usersList;
    }

    /**
     * VIEW HOLDER CLASS
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView avatar;
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    public class OnAvatarClickListener implements View.OnClickListener{

        String userName;
        public OnAvatarClickListener(String userName) {
            this.userName = userName;
        }

        @Override
        public void onClick(View v) {
            presenter.clickUser(userName);
        }
    }

}
