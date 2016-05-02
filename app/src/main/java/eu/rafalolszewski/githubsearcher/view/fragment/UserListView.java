package eu.rafalolszewski.githubsearcher.view.fragment;

import java.util.List;

import eu.rafalolszewski.githubsearcher.model.GithubUser;

/**
 * Created by rafal on 02.05.16.
 */
public interface UserListView {

    public void refreshUsers(List<GithubUser> listOfUsers);

    public void setProgressIndicator(boolean enabled);

    public void setLoadDataError(Throwable t);

}
