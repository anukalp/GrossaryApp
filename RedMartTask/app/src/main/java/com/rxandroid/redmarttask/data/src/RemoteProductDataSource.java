package com.rxandroid.redmarttask.data.src;

import android.content.Context;
import android.support.annotation.NonNull;

import com.rxandroid.redmarttask.application.RedMartApplication;
import com.rxandroid.redmarttask.data.ProductDetail;
import com.rxandroid.redmarttask.util.ActivityUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by anukalp on 22/8/17.
 */

@Singleton
@Named("RemoteProductDataSource")
public class RemoteProductDataSource implements ProductDataSource {

    @Inject
    Retrofit retrofit;

    @Inject
    Context mApplicationContext;

    @Inject
    public RemoteProductDataSource() {
        RedMartApplication.getApplicationComponent().inject(this);
    }

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

    }

    @Override
    public void refreshProducts() {

    }
}
