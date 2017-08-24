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

package com.rxandroid.redmarttask.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Strings;
import com.rxandroid.redmarttask.application.RedMartApplication;
import com.rxandroid.redmarttask.data.src.ProductDataSource;
import com.rxandroid.redmarttask.data.ProductDetail;
import com.rxandroid.redmarttask.util.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.CompositeDisposable;

import static dagger.internal.Preconditions.checkNotNull;


/**
 * Listens to user actions from the UI ({@link ProductDetailFragment}), retrieves the data and updates
 * the UI as required.
 */
public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    @NonNull
    private final ProductDetailContract.View mProductView;

    @Nullable
    private String mProductId;

    @NonNull
    private CompositeDisposable mSubscriptions;

    @Inject
    @Named("ProductDataRepository")
    ProductDataSource mProductRepository;

    @Inject
    @Named("SchedulerProvider")
    BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    @Inject
    Context mApplicationContext;

    /**
     * Creates a presenter for the add/edit view.
     *
     * @param productId   ID of the task to edit or null for a new task
     * @param addTaskView the add/edit view
     */
    public ProductDetailPresenter(@Nullable String productId,
                                  @NonNull ProductDetailContract.View addTaskView) {
        mProductId = productId;
        mProductView = checkNotNull(addTaskView);
        RedMartApplication.getApplicationComponent().inject(this);

        mSubscriptions = new CompositeDisposable();
        mProductView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        openTask();
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    private void openTask() {
        if (Strings.isNullOrEmpty(mProductId)) {
            mProductView.showMissingProduct();
            return;
        }
        mProductView.setLoadingIndicator(true);
        mSubscriptions.add(mProductRepository
                .getProduct(mProductId)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(
                        // onNext
                        this::showProduct,
                        // onError
                        throwable -> {
                        },
                        // onCompleted
                        () -> mProductView.setLoadingIndicator(false)
                ));
    }

    private void showProduct(@NonNull ProductDetail productDetail) {
        ProductDetail product = checkNotNull(productDetail);
        String title = product.getName();
        String description = product.getDescription();

        if (Strings.isNullOrEmpty(title)) {
            mProductView.hideTitle();
        } else {
            mProductView.showTitle(title);
        }

        if (Strings.isNullOrEmpty(description)) {
            mProductView.hideDescription();
        } else {
            mProductView.showDescription(description);
        }
        mProductView.showCompletionStatus(product.isCompleted());
    }

}
