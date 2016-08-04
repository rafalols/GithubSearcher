package eu.rafalolszewski.githubsearcher.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Rafał Olszewski (rafal.olszewski@hycom.pl) on 04.08.16.
 */
@Parcel
public class RepoOwner {

    public int id;

    public String login;

    @SerializedName("avatar_url")
    public String avatar;

    public String url;

    public String type;

}
