package com.rxandroid.redmarttask.data.src.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.rxandroid.redmarttask.application.RedMartApplication;
import com.rxandroid.redmarttask.data.ProductDetail;
import com.rxandroid.redmarttask.data.src.ProductDataSource;
import com.rxandroid.redmarttask.data.src.network.pojo.DetailResponse;
import com.rxandroid.redmarttask.data.src.network.pojo.ListingResponse;
import com.rxandroid.redmarttask.data.src.network.pojo.Product;
import com.rxandroid.redmarttask.util.AppConstants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by anukalp on 22/8/17.
 */

@Singleton
@Named("RemoteProductDataSource")
public class RemoteProductDataSource implements ProductDataSource {

    @Inject
    RetrofitProductData retrofitProductData;

    @Inject
    Context mApplicationContext;

    @NonNull
    private Function<ListingResponse, List<ProductDetail>> mProductsMapperFunction;
    private Function<DetailResponse, ProductDetail> mProductMapperFunction;

    @Inject
    public RemoteProductDataSource() {
        RedMartApplication.getApplicationComponent().inject(this);
        mProductsMapperFunction = this::getProductFromResponse;
        mProductMapperFunction = this:: getProductFromDetailResponse;
    }

    private ProductDetail getProductFromDetailResponse(DetailResponse detailResponse) {
        Product product = detailResponse.getProduct();
        String imageName = AppConstants.IMAGE_BASE_URL + product.getImages().get(0).getName();
        return new ProductDetail(product.getTitle(), product.getDesc(), String.valueOf(product.getId()), imageName, product.getPricing(), product.getMeasure().getMeasureMetric());
    }

    private List<ProductDetail> getProductFromResponse(ListingResponse listingResponse) {
        List<Product> products = listingResponse.getProducts();
        List<ProductDetail> productDetailList = new ArrayList<>();
        for (Product product : products) {
            String imageName = AppConstants.IMAGE_BASE_URL + product.getImages().get(0).getName();
            productDetailList.add(new ProductDetail(product.getTitle(), product.getDesc(), String.valueOf(product.getId()), imageName, product.getPricing(), product.getMeasure().getMeasureMetric()));
        }
        return productDetailList;
    }

    @Override
    public Observable<List<ProductDetail>> getProducts(int pageNo) {
        return retrofitProductData.requestProductListObservable(pageNo, AppConstants.MAX_ITEMS_PER_REQUEST).map(mProductsMapperFunction);
    }

    @Override
    public Observable<ProductDetail> getProduct(@NonNull String productId) {
        return retrofitProductData.requestProductObservable(productId).map(mProductMapperFunction);
    }

    @Override
    public void addProductsToCart(@NonNull String productId) {
        //TODO: Implement an API call to checkout product to cart, currently not provided
    }

    @Override
    public void refreshProducts() {
        //TODO: post call to tell api to invalidate cache
    }
}
