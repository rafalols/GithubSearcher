package eu.rafalolszewski.githubsearcher.ui.base;

import android.os.Bundle;

/**
 * Created by rafal on 02.05.16.
 */
public interface BasePresenter {

    void onCreate(Bundle savedInstanceState);

    void onSave(Bundle outState);

}
