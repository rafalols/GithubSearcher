package eu.rafalolszewski.githubsearcher.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.rafalolszewski.githubsearcher.GitHubSearcherApplication;
import eu.rafalolszewski.githubsearcher.dao.HistoryDaoImpl;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by rafal on 02.05.16.
 */
@Module
public class ApplicationModule {

    GitHubSearcherApplication application;

    public ApplicationModule(GitHubSearcherApplication application){
        this.application = application;
    }

    @Singleton
    @Provides
    GitHubSearcherApplication providesApplication(){
        return application;
    }

    @Singleton
    @Provides
    RealmConfiguration providesRealmConfiguration(){
        return new RealmConfiguration.Builder(application)
                .name("searchhistory.realm")
                .schemaVersion(1)
                .build();
    }

    @Singleton
    @Provides
    Realm providesRealm(RealmConfiguration realmConfiguration){
        return Realm.getInstance(realmConfiguration);
    }

    @Singleton
    @Provides
    HistoryDaoImpl providesHistoryDaoImpl(Realm realm){
        return new HistoryDaoImpl(realm);
    }

}
