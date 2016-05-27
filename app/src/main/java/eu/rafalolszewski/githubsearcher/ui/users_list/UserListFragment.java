package eu.rafalolszewski.githubsearcher.ui.users_list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import eu.rafalolszewski.githubsearcher.R;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserListFragment extends Fragment implements UserListVP.View{

    private static final String TAG = "UserListFragment";

    @Bind(R.id.label_users)
    TextView usersLabel;

    @Bind(R.id.recycler_user_list)
    RecyclerView recyclerView;

    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    @Bind(R.id.error)
    TextView errorTextView;

    RecyclerView.LayoutManager rwLayoutManager;

    @Inject
    UserListAdapter rwAdapter;

    @Inject
    UserListVP.Presenter presenter;

    public UserListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onGetUsersList(GithubUsersSearch usersList) {
        usersLabel.setText(getString(R.string.Users) + " found " + usersList.count);
        synchronized (rwAdapter) {
            rwAdapter.setUsersList(usersList);
            rwAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setProgressIndicator(boolean enabled) {
        if (enabled){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setupRecyclerView() {
        rwLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(rwLayoutManager);
        recyclerView.setAdapter(rwAdapter);
    }

    @Override
    public void setLoadDataError(Throwable t) {
        errorTextView.setVisibility(View.VISIBLE);
    }

}
