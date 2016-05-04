package eu.rafalolszewski.githubsearcher.view.presenter;

import android.os.Bundle;

import eu.rafalolszewski.githubsearcher.view.fragment.BaseView;

/**
 * Created by rafal on 02.05.16.
 */
public interface BasePresenter {

    public void onCreate(Bundle savedInstanceState);

    public void onSave(Bundle outState);

    public void onStop();

    public void setView(BaseView view);

}
