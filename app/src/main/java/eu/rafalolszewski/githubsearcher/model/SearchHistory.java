package eu.rafalolszewski.githubsearcher.model;

import com.orm.SugarRecord;

/**
 * Created by rafal on 02.05.16.
 */
public class SearchHistory extends SugarRecord {

    public String searchString;
    public long searchDate;
    public int numberOdResults;


    public SearchHistory(){}

    public SearchHistory(String searchString, long searchDate, int numberOdResults) {
        this.searchString = searchString;
        this.searchDate = searchDate;
        this.numberOdResults = numberOdResults;
    }
}
