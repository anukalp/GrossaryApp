package com.rxandroid.redmarttask.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import java.util.UUID;

/**
 * Created by anukalp on 22/8/17.
 */

public final class ProductDetail {

    @NonNull
    private final String mId;

    @Nullable
    private final String mImageUrl;

    @Nullable
    private final String mName;

    @Nullable
    private final String mDescription;

    @Nullable
    private final String mPrice;

    private final boolean mCompleted;

    /**
     * Use this constructor to create a new active Task.
     *
     * @param title       title of the product
     * @param description description of the product
     * @param mImageUrl   imageUrl of the product
     * @param mPrice      price of the product
     */
    public ProductDetail(@Nullable String title, @Nullable String description, String mImageUrl, String mPrice) {
        this(title, description, UUID.randomUUID().toString(), mImageUrl, mPrice, false);
    }

    /**
     * Use this constructor to create an active Task if the Task already has an id (copy of another
     * Task).
     *
     * @param title       title of the product
     * @param description description of the product
     * @param id          id of the product
     * @param mImageUrl   imageUrl of the product
     * @param mPrice      price of the product
     */
    public ProductDetail(@Nullable String title, @Nullable String description, @NonNull String id, String mImageUrl, String mPrice) {
        this(title, description, id, mImageUrl, mPrice, false);
    }

    /**
     * Use this constructor to create a new completed Task.
     *
     * @param title       title of the product
     * @param description description of the product
     * @param completed   true if the product is completed, false if it's active
     * @param mImageUrl   imageUrl of the product
     * @param mPrice      price of the product
     */
    public ProductDetail(@Nullable String title, @Nullable String description, boolean completed, String mImageUrl, String mPrice) {
        this(title, description, UUID.randomUUID().toString(), mImageUrl, mPrice, completed);
    }

    /**
     * Use this constructor to specify a completed Task if the Task already has an id (copy of
     * another Task).
     *
     * @param title       title of the product
     * @param description description of the product
     * @param id          id of the product
     * @param mImageUrl   imageUrl of the product
     * @param mPrice      price of the product
     * @param completed   true if the product details is completed, false if it's active
     */
    public ProductDetail(@Nullable String title, @Nullable String description,
                         @NonNull String id, String mImageUrl, String mPrice, boolean completed) {
        mId = id;
        mName = title;
        mDescription = description;
        this.mImageUrl = mImageUrl;
        this.mPrice = mPrice;
        mCompleted = completed;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @Nullable
    public String getName() {
        return mName;
    }

    @Nullable
    public String getTitleForList() {
        if (!Strings.isNullOrEmpty(mName)) {
            return mName;
        } else {
            return mDescription;
        }
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public boolean isActive() {
        return !mCompleted;
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(mName) &&
                Strings.isNullOrEmpty(mDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetail productDetail = (ProductDetail) o;
        return Objects.equal(mId, productDetail.mId) &&
                Objects.equal(mName, productDetail.mName) &&
                Objects.equal(mDescription, productDetail.mDescription) &&
                Objects.equal(mPrice, productDetail.mPrice) &&
                Objects.equal(mImageUrl, productDetail.mImageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId, mName, mDescription, mPrice);
    }

    @Nullable
    public String getImageUrl() {
        return mImageUrl;
    }

    @Nullable
    public String getPrice() {
        return mPrice;
    }

    @Override
    public String toString() {
        return "Product title " + mName;
    }
}
