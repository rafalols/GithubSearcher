package eu.rafalolszewski.githubsearcher.view.presenter;

/**
 * Created by rafal on 02.05.16.
 */
public interface UserListPresenter extends BasePresenter{

    public void getUserList(String searchString);

    public void clickUser(String name);

}