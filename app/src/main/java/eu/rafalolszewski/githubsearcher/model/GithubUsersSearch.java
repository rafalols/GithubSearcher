package eu.rafalolszewski.githubsearcher.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by rafal on 02.05.16.
 */
@Parcel
public class GithubUsersSearch {

    @SerializedName("total_count")
    public int count;

    @SerializedName("items")
    public List<UserPreview> usersPreviews;

}
