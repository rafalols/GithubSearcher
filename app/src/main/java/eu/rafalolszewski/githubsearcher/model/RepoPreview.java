package eu.rafalolszewski.githubsearcher.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Date;

/**
 * Created by Rafał Olszewski (rafal.olszewski@hycom.pl) on 04.08.16.
 */
@Parcel
public class RepoPreview {

    public int id;

    public String name;

    @SerializedName("full_name")
    public String fullName;

    public RepoOwner owner;

    @SerializedName("private")
    boolean priv;

    @SerializedName("html_url")
    public String url;

    public String description;

    public boolean fork;

    public Date createdAt;

    public Date updatedAt;

    public Date pushedAt;

    public int size;

    @SerializedName("stargazers_count")
    public int stargazers;

    @SerializedName("watchers_count")
    public int watchers;

    public String language;

    @SerializedName("forks_count")
    public int forks;

    @SerializedName("open_issues_count")
    public int openIssues;

    @SerializedName("master_branch")
    public String masterBranch;

    @SerializedName("default_branch")
    public String defaultBranch;

}
