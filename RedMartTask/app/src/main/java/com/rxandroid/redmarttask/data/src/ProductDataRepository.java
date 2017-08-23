package com.rxandroid.redmarttask.data.src;

import android.support.annotation.NonNull;

import com.rxandroid.redmarttask.data.ProductDetail;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by anukalp on 23/8/17.
 */

public class ProductDataRepository implements ProductDataSource {


    @Override
    public Observable<List<ProductDetail>> getProducts(int startOffSet) {
        return null;
    }

    @Override
    public Observable<ProductDetail> getProduct(@NonNull String productId) {
        return null;
    }

    @Override
    public void saveProducts(@NonNull List<ProductDetail> productDetails) {
        //TODO: Add New products implementation for server
    }

    @Override
    public void refreshProducts() {

    }
}
