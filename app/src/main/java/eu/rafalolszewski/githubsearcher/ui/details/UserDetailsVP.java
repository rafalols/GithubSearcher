package eu.rafalolszewski.githubsearcher.ui.details;

import eu.rafalolszewski.githubsearcher.model.UserDetails;
import eu.rafalolszewski.githubsearcher.ui.base.BasePresenter;

/**
 * Created by rafal on 23.05.16.
 */

public interface UserDetailsVP {

    interface View {

        void onGetUser(UserDetails user);

        void setProgressIndicator(boolean enabled);

        void animateFabButton();

        void setLoadDataError(Throwable t);
    }

    interface Presenter extends BasePresenter{

        public static final String ARG_USERNAME = "username";

        void getUserByLogin(String userId);

        void onLoadedImage(boolean succedd);

        void onTransitionFinished();

        void openUserProfile();
    }

}
