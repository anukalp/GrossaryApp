package com.rxandroid.redmarttask.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rxandroid.redmarttask.data.src.ProductDataRepository;
import com.rxandroid.redmarttask.data.src.ProductDataSource;
import com.rxandroid.redmarttask.data.src.local.LocalProductDataSource;
import com.rxandroid.redmarttask.data.src.network.RemoteProductDataSource;
import com.rxandroid.redmarttask.data.src.network.RequestLoggingInterceptor;
import com.rxandroid.redmarttask.data.src.network.RetrofitProductData;
import com.rxandroid.redmarttask.util.AppConstants;
import com.rxandroid.redmarttask.util.schedulers.BaseSchedulerProvider;
import com.rxandroid.redmarttask.util.schedulers.SchedulerProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    RequestLoggingInterceptor provideHttpLoggingInterceptor() {
        return new RequestLoggingInterceptor();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(RequestLoggingInterceptor interceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .connectTimeout(AppConstants.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(AppConstants.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(AppConstants.TIMEOUT, TimeUnit.SECONDS);

        return client.build();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, RxJava2CallAdapterFactory rxJava2CallAdapterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
    }


    @Provides
    @Singleton
    public RetrofitProductData provideApiInterface(Retrofit retrofit) {
        return retrofit.create(RetrofitProductData.class);
    }

    @Singleton
    @Named("SchedulerProvider")
    @Provides
    BaseSchedulerProvider provideScheduler() {
        return new SchedulerProvider();
    }

    @Singleton
    @Named("RemoteProductDataSource")
    @Provides
    ProductDataSource providesProductDataSource() {
        return new RemoteProductDataSource();
    }

    @Singleton
    @Named("LocalProductDataSource")
    @Provides
    ProductDataSource providesLocalProductDataSource() {
        return new LocalProductDataSource();
    }

    @Singleton
    @Named("ProductDataRepository")
    @Provides
    ProductDataSource providesProductDataRepository() {
        return new ProductDataRepository();
    }

}
