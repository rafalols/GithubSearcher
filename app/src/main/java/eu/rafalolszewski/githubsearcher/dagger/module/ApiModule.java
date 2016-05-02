package eu.rafalolszewski.githubsearcher.dagger.module;

import java.util.concurrent.TimeUnit;

import dagger.producers.ProducerModule;
import dagger.producers.Produces;
import eu.rafalolszewski.githubsearcher.GitHubSearcherApplication;
import eu.rafalolszewski.githubsearcher.api.GitApiService;
import eu.rafalolszewski.githubsearcher.api.GitHubApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rafal on 02.05.16.
 */
@ProducerModule
public class ApiModule {

    @Produces
    public OkHttpClient produceOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        return builder.build();
    }

    @Produces
    public Retrofit produceRetrofit(GitHubSearcherApplication application, OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    @Produces
    public GitApiService produceGitApiService(Retrofit retrofit){
        return retrofit.create(GitApiService.class);
    }

    @Produces
    public GitHubApi produceGitHubApi(GitApiService gitApiService){
        return new GitHubApi(gitApiService);
    }

}
