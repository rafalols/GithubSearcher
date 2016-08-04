package eu.rafalolszewski.githubsearcher.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by rafal on 02.05.16.
 */
@Parcel
public class UserDetails {

    public int id;

    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;

    @SerializedName("html_url")
    public String htmlUrl;

    public String name;

    public String company;

    public String location;

    public String email;

    @SerializedName("public_repos")
    public int publicRepos;

    @SerializedName("public_gists")
    public int publicGists;

    public int followers;

    public int following;

    public String type;

}
