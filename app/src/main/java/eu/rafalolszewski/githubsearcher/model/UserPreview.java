package eu.rafalolszewski.githubsearcher.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by rafal on 04.05.16.
 */
@Parcel
public class UserPreview{

    public int id;

    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;

    public String type;


}
