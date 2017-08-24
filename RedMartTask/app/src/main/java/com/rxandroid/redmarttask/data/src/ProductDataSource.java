package com.rxandroid.redmarttask.data.src;

import android.support.annotation.NonNull;

import com.rxandroid.redmarttask.data.ProductDetail;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by anukalp on 22/8/17.
 */

public interface ProductDataSource {

    Observable<List<ProductDetail>> getProducts(int startOffSet);

    Observable<ProductDetail> getProduct(@NonNull String productId);

    void addProductsToCart(@NonNull String productId);

    void refreshProducts();
}
