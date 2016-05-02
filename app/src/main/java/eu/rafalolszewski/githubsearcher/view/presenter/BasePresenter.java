package eu.rafalolszewski.githubsearcher.view.presenter;

import android.os.Bundle;

/**
 * Created by rafal on 02.05.16.
 */
public interface BasePresenter {

    public void onCreate(Bundle savedInstanceState);

    public void onSave(Bundle bundle);

    public void onStop();

}
