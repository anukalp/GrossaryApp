package com.rxandroid.redmarttask.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rxandroid.redmarttask.data.src.ProductDataSource;
import com.rxandroid.redmarttask.data.src.RemoteProductDataSource;
import com.rxandroid.redmarttask.util.schedulers.BaseSchedulerProvider;
import com.rxandroid.redmarttask.util.schedulers.SchedulerProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anukalp on 22/8/17.
 */

@Module
public class RemoteModule {

    private final String mBaseUrl;

    public RemoteModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().
                connectTimeout(60 * 1000, TimeUnit.MILLISECONDS).
                readTimeout(60 * 1000, TimeUnit.MILLISECONDS).
                build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }

    @Named("SchedulerProvider")
    @Singleton
    @Provides
    BaseSchedulerProvider provideScheduler() {
        return new SchedulerProvider();
    }

    @Named("RemoteProductDataSource")
    @Singleton
    @Provides
    ProductDataSource providesProductDataSource() {
        return new RemoteProductDataSource();
    }

}
