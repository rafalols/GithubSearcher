package eu.rafalolszewski.githubsearcher.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rafał Olszewski (rafal.olszewski@hycom.pl) on 04.08.16.
 */
public class GithubRepoSearch {

    @SerializedName("total_count")
    public int count;

    @SerializedName("items")
    public List<RepoPreview> repos;

}
