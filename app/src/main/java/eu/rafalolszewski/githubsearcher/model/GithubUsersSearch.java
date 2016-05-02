package eu.rafalolszewski.githubsearcher.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rafal on 02.05.16.
 */
public class GithubUsersSearch {

    @SerializedName("total_count")
    int count;

    @SerializedName("items")
    List<UserPreview> usersPreviews;

    public class UserPreview{

        int id;

        String login;

        @SerializedName("avatar_url")
        String avatarUrl;

        String type;
    }

}
