package eu.rafalolszewski.githubsearcher.ui.users_list;

import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import eu.rafalolszewski.githubsearcher.ui.base.BasePresenter;

/**
 * Created by rafal on 23.05.16.
 */

public interface UserListVP {

    interface View{

        void onGetUsersList(GithubUsersSearch usersList);

        void setProgressIndicator(boolean enabled);

        void setLoadDataError(Throwable t);

        void setupRecyclerView();
    }

    interface Presenter extends BasePresenter{

        public static final String SEARCH_RESULT = "searchResults";

        void getUserList(String searchString);

        void clickUser(String name, android.view.View avatarForTransition, android.view.View loginForTransition);
    }

}
