package eu.rafalolszewski.githubsearcher.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rafal on 02.05.16.
 */
public class GithubUser {

    int id;

    String login;

    @SerializedName("avatar_url")
    String avatarUrl;

    @SerializedName("html_url")
    String htmlUrl;

    String name;

    String company;

    String location;

    String email;

    @SerializedName("public_repos")
    int publicRepos;

    @SerializedName("public_gists")
    int publicGists;

    int followers;

    int following;

    String type;

}
