package eu.rafalolszewski.githubsearcher.ui.details;

import eu.rafalolszewski.githubsearcher.model.GithubUser;
import eu.rafalolszewski.githubsearcher.ui.base.BasePresenter;
import eu.rafalolszewski.githubsearcher.ui.base.BaseView;

/**
 * Created by rafal on 23.05.16.
 */

public interface UserDetailsVP {

    interface View extends BaseView{

        void onGetUser(GithubUser user);

        void setProgressIndicator(boolean enabled);

    }

    interface Presenter extends BasePresenter{

        public static final String ARG_USERNAME = "username";

        void getUserByName(String userId);

    }

}
