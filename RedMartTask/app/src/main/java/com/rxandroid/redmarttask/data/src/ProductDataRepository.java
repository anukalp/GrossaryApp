package com.rxandroid.redmarttask.data.src;

import android.support.annotation.NonNull;

import com.rxandroid.redmarttask.application.RedMartApplication;
import com.rxandroid.redmarttask.data.ProductDetail;
import com.rxandroid.redmarttask.data.src.local.LocalProductDataSource;
import com.rxandroid.redmarttask.data.src.network.RemoteProductDataSource;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by anukalp on 23/8/17.
 */

public class ProductDataRepository implements ProductDataSource {

    @Inject
    RemoteProductDataSource mRemoteDataSource;

    @Inject
    LocalProductDataSource mLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, ProductDetail> mCachedTasks;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;


    @Inject
    public ProductDataRepository() {
        RedMartApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<List<ProductDetail>> getProducts(int pageNo) {
        return mRemoteDataSource.getProducts(pageNo);
    }

    @Override
    public Observable<ProductDetail> getProduct(@NonNull String productId) {
        return mRemoteDataSource.getProduct(productId);
    }

    @Override
    public void addProductsToCart(@NonNull String productId) {
        //TODO: Implement add to cart functionality
    }

    @Override
    public void refreshProducts() {
        //TODO: RefreshProductList
        mCacheIsDirty = true;
    }
}
