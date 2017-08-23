package com.rxandroid.redmarttask.dagger;

import com.rxandroid.redmarttask.application.RedMartApplication;
import com.rxandroid.redmarttask.data.src.LocalProductDataSource;
import com.rxandroid.redmarttask.data.src.RemoteProductDataSource;
import com.rxandroid.redmarttask.detail.ProductDetailActivity;
import com.rxandroid.redmarttask.detail.ProductDetailFragment;
import com.rxandroid.redmarttask.detail.ProductDetailPresenter;
import com.rxandroid.redmarttask.listing.ProductListActivity;
import com.rxandroid.redmarttask.listing.ProductListingPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component class for Dagger DI Framework. <br>
 * Any activities/class requiring Injected fields need to have an inject function here
 * Created by mmt5997 on 03/05/16.
 */
@Singleton
@Component(modules = {RemoteModule.class, ApplicationModule.class})
public interface ApplicationComponent {

    void inject(RedMartApplication application);

    void inject(RemoteProductDataSource dataSource);

    void inject(ProductDetailPresenter productDetailPresenter);

    void inject(ProductListingPresenter productListingPresenter);

    void inject(LocalProductDataSource productDataSource);

}
