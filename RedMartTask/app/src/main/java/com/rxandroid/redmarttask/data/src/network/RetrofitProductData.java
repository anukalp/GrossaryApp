package com.rxandroid.redmarttask.data.src.network;

import com.rxandroid.redmarttask.data.ProductDetail;
import com.rxandroid.redmarttask.data.src.network.pojo.DetailResponse;
import com.rxandroid.redmarttask.data.src.network.pojo.ListingResponse;
import com.rxandroid.redmarttask.util.AppConstants;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by anukalp on 24/8/17.
 */

public interface RetrofitProductData {

    /**
     * API is used to get product list
     *
     * @return
     */
    @Headers({AppConstants.API_CONSTANTS.CONTENT_TYPE_APPLICATION_JSON})
    @GET(AppConstants.API_CONSTANTS.REQUEST_PRODUCTS)
    Single<ListingResponse> requestProductListObservable(@Query(AppConstants.API_CONSTANTS.PAGE_NO) int pageNo, @Query(AppConstants.API_CONSTANTS.PAGE_SIZE) int pageSize);

    /**
     * API for product detail
     *
     * @param id : requested product id
     * @return
     */
    @Headers({AppConstants.API_CONSTANTS.CONTENT_TYPE_APPLICATION_JSON})
    @GET(AppConstants.API_CONSTANTS.REQUEST_PRODUCT)
    Single<DetailResponse> requestProductObservable(@Path(value = AppConstants.API_CONSTANTS.PRODUCT_ID) String id);


}