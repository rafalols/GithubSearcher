package eu.rafalolszewski.githubsearcher.ui.result;

import android.app.Activity;

import eu.rafalolszewski.githubsearcher.model.GithubRepoSearch;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import eu.rafalolszewski.githubsearcher.ui.base.BasePresenter;

/**
 * Created by Rafał Olszewski (rafal.olszewski@hycom.pl) on 04.08.16.
 */
public interface ResultVP {

    interface View{

        void refreshUsers(GithubUsersSearch usersList);

        void refreshRepos(GithubRepoSearch reposList);

        void setUsersProgressIndicator(boolean enabled);

        void setReposProgressIndicator(boolean enabled);

        void setLoadUsersError(boolean status);

        void setLoadReposError(boolean status);

        Activity getActivity();
    }

    interface Presenter extends BasePresenter {

        void search(String searchString);

        void clickUser(String name, android.view.View avatarForTransition, android.view.View loginForTransition);

        void clickRepo();

    }


}
