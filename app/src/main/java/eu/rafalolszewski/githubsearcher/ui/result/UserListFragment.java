package eu.rafalolszewski.githubsearcher.ui.result;

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


public class UserListFragment extends Fragment {

    private static final String SEARCH_STRING = "searchString";

    @Bind(R.id.label_users)
    TextView usersLabel;

    @Bind(R.id.recycler_user_list)
    RecyclerView list;

    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    @Bind(R.id.error)
    TextView errorTextView;

    RecyclerView.LayoutManager rwLayoutManager;

    @Inject
    UserListAdapter rwAdapter;

    @Inject
    ResultVP.Presenter presenter;

    String searchString;

    public UserListFragment() {
        // Required empty public constructor
    }

    public static UserListFragment newInstance(String searchString){
        UserListFragment fragment = new UserListFragment();

        Bundle b = new Bundle();
        b.putString(SEARCH_STRING, searchString);
        fragment.setArguments(b);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null){
            searchString = savedInstanceState.getString(SEARCH_STRING);
        }else {
            searchString = getArguments().getString(SEARCH_STRING);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(SEARCH_STRING, searchString);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_user_list, container, false);

        ButterKnife.bind(this, view);

        presenter.search(searchString);

        setupRecyclerView();

        return view;
    }


    public void setupRecyclerView() {
        rwLayoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(rwLayoutManager);
        list.setAdapter(rwAdapter);
    }

    public void refreshUsers(GithubUsersSearch usersList) {
        usersLabel.setText(getString(R.string.Users) + " found " + usersList.count);
        synchronized (rwAdapter) {
            rwAdapter.setUsersList(usersList);
            rwAdapter.notifyDataSetChanged();
        }
    }

    public void setProgressIndicator(boolean enabled) {
        if (enabled){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void setLoadDataError(boolean status) {
        errorTextView.setVisibility(status ? View.VISIBLE : View.INVISIBLE);
    }

}
