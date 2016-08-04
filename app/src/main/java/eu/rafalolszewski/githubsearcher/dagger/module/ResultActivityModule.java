package eu.rafalolszewski.githubsearcher.dagger.module;

import android.support.test.espresso.idling.CountingIdlingResource;

import dagger.Module;
import dagger.Provides;
import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import eu.rafalolszewski.githubsearcher.dagger.scope.PerResultActivity;
import eu.rafalolszewski.githubsearcher.dao.HistoryDaoImpl;
import eu.rafalolszewski.githubsearcher.ui.result.RepoListAdapter;
import eu.rafalolszewski.githubsearcher.ui.result.RepoListFragment;
import eu.rafalolszewski.githubsearcher.ui.result.ResultActivity;
import eu.rafalolszewski.githubsearcher.ui.result.ResultPresenter;
import eu.rafalolszewski.githubsearcher.ui.result.ResultVP;
import eu.rafalolszewski.githubsearcher.ui.result.UserListAdapter;
import eu.rafalolszewski.githubsearcher.ui.result.UserListFragment;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by rafal on 02.05.16.
 */
@Module
public class ResultActivityModule {

    private ResultActivity resultActivity;
    private GitHubApi api;

    public ResultActivityModule(ResultActivity resultActivity, GitHubApi api) {
        this.resultActivity = resultActivity;
        this.api = api;
    }

    @Provides
    @PerResultActivity
    UserListFragment providesUserFragment(){
        return UserListFragment.newInstance(resultActivity.searchString);
    }

    @Provides
    @PerResultActivity
    RepoListFragment providesReposFragment(){
        return new RepoListFragment();
    }

    @Provides
    @PerResultActivity
    ResultVP.Presenter providesUserListPresenter(HistoryDaoImpl historyDao, CountingIdlingResource idlingResource){
        return new ResultPresenter(resultActivity, api, historyDao, AndroidSchedulers.mainThread(), idlingResource);
    }

    @Provides
    @PerResultActivity
    UserListAdapter providesUserListAdapter(ResultVP.Presenter presenter){
        return new UserListAdapter(resultActivity, presenter);
    }

    @Provides
    @PerResultActivity
    RepoListAdapter providesRepoListAdapter(ResultVP.Presenter presenter){
        return new RepoListAdapter(resultActivity, presenter);
    }

}
