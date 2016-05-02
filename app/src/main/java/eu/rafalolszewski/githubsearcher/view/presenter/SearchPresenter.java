package eu.rafalolszewski.githubsearcher.view.presenter;

/**
 * Created by rafal on 02.05.16.
 */
public interface SearchPresenter extends BasePresenter {

    public static final String SEARCH_STRING= "searchString";

    public void search(String search);

}
