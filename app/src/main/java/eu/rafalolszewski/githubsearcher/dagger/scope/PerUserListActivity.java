package eu.rafalolszewski.githubsearcher.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by rafal on 02.05.16.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerUserListActivity {
}
