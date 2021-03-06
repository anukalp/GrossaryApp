package com.rxandroid.redmarttask.data.src;

import android.support.annotation.NonNull;

import com.rxandroid.redmarttask.application.RedMartApplication;
import com.rxandroid.redmarttask.data.ProductDetail;
import com.rxandroid.redmarttask.data.src.local.LocalProductDataSource;
import com.rxandroid.redmarttask.data.src.network.RemoteProductDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

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


    private Observable<List<ProductDetail>> getAndCacheLocalProducts(int pageNo) {
        return mLocalDataSource.getProducts(pageNo)
                .flatMap(productDetails -> Observable.fromIterable(productDetails).doOnNext(productDetail -> {
                    mCachedTasks.put(productDetail.getId(), productDetail);
                }).toList().toObservable());
    }

    private Observable<List<ProductDetail>> getAndSaveRemoteProducts(int pageNo) {
        return mRemoteDataSource
                .getProducts(pageNo)
                .flatMap(productDetails -> {
                    mLocalDataSource.saveProducts(productDetails);
                    return Observable.fromIterable(productDetails).doOnNext(productDetail -> {
                        mCachedTasks.put(productDetail.getId(), productDetail);
                    }).toList().toObservable();
                })
                .doOnDispose(() -> mCacheIsDirty = false);
    }

    /**
     * Gets tasks from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     */
    @Override
    public Observable<List<ProductDetail>> getProducts(int pageNo) {
        return mRemoteDataSource
                .getProducts(pageNo);
       /* // Respond immediately with cache if available and not dirty
        if (mCachedTasks != null && !mCacheIsDirty) {
            return Observable.fromIterable(mCachedTasks.values()).toList().toObservable();
        } else if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();

        Observable<List<ProductDetail>> remoteProducts = getAndSaveRemoteProducts(pageNo);
        if (mCacheIsDirty) {
            return remoteProducts;
        } else {
            // Query the local storage if available. If not, query the network.
            Observable<List<ProductDetail>> localProducts = getAndCacheLocalProducts(pageNo);
            List<ProductDetail> defaultProducts = new ArrayList<>(mCachedTasks.values());
            return Observable.concat(localProducts, remoteProducts)
                    .filter(productDetail -> !productDetail.isEmpty()).first(defaultProducts).toObservable();
        }*/
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
