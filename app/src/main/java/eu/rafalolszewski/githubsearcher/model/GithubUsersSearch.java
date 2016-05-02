package eu.rafalolszewski.githubsearcher.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rafal on 02.05.16.
 */
public class GithubUsersSearch {

    @SerializedName("total_count")
    public int count;

    @SerializedName("items")
    public List<UserPreview> usersPreviews;

    public class UserPreview{

        public int id;

        public String login;

        @SerializedName("avatar_url")
        public String avatarUrl;

        public String type;
    }

}
