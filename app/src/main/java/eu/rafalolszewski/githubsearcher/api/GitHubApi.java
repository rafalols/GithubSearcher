package eu.rafalolszewski.githubsearcher.api;

import java.util.List;

import eu.rafalolszewski.githubsearcher.model.GithubUser;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import rx.Observable;

/**
 * Created by rafal on 02.05.16.
 */
public class GitHubApi {

    GitApiService service;

    public GitHubApi(GitApiService gitApiService) {
        service = gitApiService;
    }


    public Observable<GithubUsersSearch> searchForUsers(String searchForUsers){
        return service.searchUsers(searchForUsers);
    }

    public Observable<GithubUser> getUser(String userName){
        return service.getUser(userName);
    }

    public Observable<GithubUser> getUsers(List<String> users) {
        return Observable.from(users)
                .flatMap(user -> service.getUser(user));
    }

}
