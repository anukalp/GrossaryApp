package com.rxandroid.redmarttask.dagger;

import com.rxandroid.redmarttask.application.RedMartApplication;
import com.rxandroid.redmarttask.data.src.local.LocalProductDataSource;
import com.rxandroid.redmarttask.data.src.ProductDataRepository;
import com.rxandroid.redmarttask.data.src.network.RemoteProductDataSource;
import com.rxandroid.redmarttask.detail.ProductDetailPresenter;
import com.rxandroid.redmarttask.listing.ProductListingPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component class for Dagger DI Framework. <br>
 * Any activities/class requiring Injected fields need to have an inject function here
 * Created by mmt5621 anukalp on 03/05/16.
 */
@Singleton
@Component(modules = {RemoteModule.class, ApplicationModule.class})
public interface ApplicationComponent {

    void inject(RedMartApplication application);

    void inject(RemoteProductDataSource dataSource);

    void inject(ProductDetailPresenter productDetailPresenter);

    void inject(ProductListingPresenter productListingPresenter);

    void inject(LocalProductDataSource productDataSource);

    void inject(ProductDataRepository dataRepository);

}
