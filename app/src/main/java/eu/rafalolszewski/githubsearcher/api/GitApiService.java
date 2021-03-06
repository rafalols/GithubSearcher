package eu.rafalolszewski.githubsearcher.api;

import eu.rafalolszewski.githubsearcher.model.GithubUser;
import eu.rafalolszewski.githubsearcher.model.GithubUsersSearch;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rafal on 02.05.16.
 */
public interface GitApiService {

    @GET("/users/{username}")
    Observable<GithubUser> getUser(
            @Path("username") String username
    );

    @GET("/search/users")
    Observable<GithubUsersSearch> searchUsers(
            @Query("q") String q,
            @Query("per_page") int perPage
    );

}