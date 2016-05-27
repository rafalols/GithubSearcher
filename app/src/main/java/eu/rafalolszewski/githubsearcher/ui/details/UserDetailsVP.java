package eu.rafalolszewski.githubsearcher.ui.details;

import eu.rafalolszewski.githubsearcher.model.GithubUser;
import eu.rafalolszewski.githubsearcher.ui.base.BasePresenter;

/**
 * Created by rafal on 23.05.16.
 */

public interface UserDetailsVP {

    interface View {

        void onGetUser(GithubUser user);

        void setProgressIndicator(boolean enabled);

        void animateFabButton();
    }

    interface Presenter extends BasePresenter{

        public static final String ARG_USERNAME = "username";

        void getUserByLogin(String userId);

        void onLoadedImage(boolean succedd);

        void onTransitionFinished();

        void openUserProfile();
    }

}
