/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rxandroid.redmarttask.listing;

import android.content.Context;
import android.support.annotation.NonNull;

import com.rxandroid.redmarttask.application.RedMartApplication;
import com.rxandroid.redmarttask.data.ProductDetail;
import com.rxandroid.redmarttask.data.src.ProductDataSource;
import com.rxandroid.redmarttask.util.ActivityUtils;
import com.rxandroid.redmarttask.util.schedulers.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static dagger.internal.Preconditions.checkNotNull;


/**
 * Listens to user actions from the UI ({@link ProductListActivity}), retrieves the data and updates
 * the UI as required.
 */
public class ProductListingPresenter implements ProductListingContract.Presenter {

    @NonNull
    private final ProductListingContract.View mProductView;

    @NonNull
    private CompositeDisposable mSubscriptions;

    @NonNull
    @Inject
    @Named("RemoteProductDataSource")
    ProductDataSource mProductRepository;

    @NonNull
    @Inject
    @Named("SchedulerProvider")
    BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    @Inject
    Context mApplicationContext;
    private boolean mFirstLoad = true;

    /**
     * Creates a presenter for the list view.
     *
     * @param productListView the product Listview
     */
    public ProductListingPresenter(@NonNull ProductListingContract.View productListView) {
        mProductView = checkNotNull(productListView);
        RedMartApplication.getApplicationComponent().inject(this);

        mSubscriptions = new CompositeDisposable();
        mProductView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        loadProducts(false);
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    public void loadProducts(boolean forceUpdate) {
        // Simplification for sample: a network reload will be forced on first load.
        loadProducts(forceUpdate || mFirstLoad, true, 0);
        mFirstLoad = false;
    }

    /**
     * @param forceUpdate   Pass in true to refresh the data in the {@link ProductDataSource}
     * @param showLoadingUI Pass in true to display a loading icon in the UI
     * @param startOffSet Offset value for pagination donwload
     */
    private void loadProducts(final boolean forceUpdate, final boolean showLoadingUI, int startOffSet) {
        if (showLoadingUI) {
            mProductView.setLoadingIndicator(true);
        }
        if (forceUpdate) {
            mProductRepository.refreshProducts();
        }

        mSubscriptions.clear();
        Disposable subscription = mProductRepository
                .getProducts(startOffSet)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(
                        // onNext
                        this::processProducts,
                        // onError
                        throwable -> mProductView.showLoadingProductsError(),
                        // onCompleted
                        () -> mProductView.setLoadingIndicator(false));
        mSubscriptions.add(subscription);
    }

    private void processProducts(List<ProductDetail> productDetailList) {
        mProductView.setProductList(productDetailList);
    }

    @Override
    public void loadMore(int startOffSet) {
        loadProducts(false, false, startOffSet);
    }
}
