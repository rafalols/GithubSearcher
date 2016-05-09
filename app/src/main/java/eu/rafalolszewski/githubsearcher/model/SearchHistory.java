package eu.rafalolszewski.githubsearcher.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rafal on 02.05.16.
 */
public class SearchHistory extends RealmObject {

    @PrimaryKey
    public String searchString;

    public long searchDate;

    public int numberOfResults;



    //GENERATED GETTERS AND SETTERS

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public long getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(long searchDate) {
        this.searchDate = searchDate;
    }

    public int getNumberOfResults() {
        return numberOfResults;
    }

    public void setNumberOfResults(int numberOfResults) {
        this.numberOfResults = numberOfResults;
    }
}
