package eu.rafalolszewski.githubsearcher.ui.users_list;

import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import eu.rafalolszewski.githubsearcher.ui.base.BasePresenter;
import eu.rafalolszewski.githubsearcher.ui.base.BaseView;

/**
 * Created by rafal on 23.05.16.
 */

public interface UserListVP {

    interface View extends BaseView{

        void onGetUsersList(GithubUsersSearch usersList);

        void setProgressIndicator(boolean enabled);

        void setLoadDataError(Throwable t);
    }

    interface Presenter extends BasePresenter{

        void getUserList(String searchString);

        void clickUser(String name, android.view.View avatarForTransition);
    }

}
