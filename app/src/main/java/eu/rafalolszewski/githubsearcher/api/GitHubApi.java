package eu.rafalolszewski.githubsearcher.api;

import java.util.List;

import eu.rafalolszewski.githubsearcher.model.GithubRepoSearch;
import eu.rafalolszewski.githubsearcher.model.UserDetails;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import rx.Observable;

/**
 * Created by rafal on 02.05.16.
 */
public class GitHubApi {

    private static final int PER_PAGE = 100;

    GitApiService service;

    public GitHubApi(GitApiService gitApiService) {
        service = gitApiService;
    }


    public Observable<GithubUsersSearch> searchForUsers(String searchForUsers){
        return service.searchUsers(searchForUsers, PER_PAGE);
    }

    public Observable<UserDetails> getUser(String userName){
        return service.getUser(userName);
    }

    public Observable<UserDetails> getUsers(List<String> users) {
        return Observable.from(users)
                .flatMap(user -> service.getUser(user));
    }

    public Observable<GithubRepoSearch> searchForRepos(String searchString){
        return service.searchRepos(searchString);
    }

}
