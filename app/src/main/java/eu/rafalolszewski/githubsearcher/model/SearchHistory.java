package eu.rafalolszewski.githubsearcher.model;

import com.orm.SugarRecord;

/**
 * Created by rafal on 02.05.16.
 */
public class SearchHistory extends SugarRecord {

    public String searchString;
    public long searchDate;
    public int numberOfResults;


    public SearchHistory(){

    }

    public SearchHistory(String searchString, long searchDate, int numberOfResults) {
        this.searchString = searchString;
        this.searchDate = searchDate;
        this.numberOfResults = numberOfResults;
    }
}
