package eu.rafalolszewski.githubsearcher.view.fragment;

import eu.rafalolszewski.githubsearcher.model.GithubUser;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;

/**
 * Created by rafal on 02.05.16.
 */
public interface UserListView extends BaseView {

    public void onGetUsersList(GithubUsersSearch usersList);

    public void onRefreshUser(GithubUser user);

    public void setProgressIndicator(boolean enabled);

    public void setLoadDataError(Throwable t);

}
