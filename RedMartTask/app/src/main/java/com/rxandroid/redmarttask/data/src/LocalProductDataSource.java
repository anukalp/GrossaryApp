package com.rxandroid.redmarttask.data.src;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.rxandroid.redmarttask.application.RedMartApplication;
import com.rxandroid.redmarttask.data.ProductDetail;
import com.rxandroid.redmarttask.util.ActivityUtils;
import com.rxandroid.redmarttask.util.schedulers.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static com.google.common.base.Preconditions.checkNotNull;

import com.rxandroid.redmarttask.data.src.ProductsPersistenceContract.ProductEntry;
import com.rxandroid.redmarttask.util.schedulers.SchedulerProvider;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

/**
 * Created by anukalp on 23/8/17.
 */

@Singleton
public class LocalProductDataSource implements ProductDataSource {

    @NonNull
    private final BriteDatabase mDatabaseHelper;

    @Inject
    @Named("SchedulerProvider")
    BaseSchedulerProvider mSchedulerProvider;

    @Inject
    Context mApplicationContext;

    @NonNull
    private Function<Cursor, ProductDetail> mProductMapperFunction;

    @Inject
    public LocalProductDataSource() {
        RedMartApplication.getApplicationComponent().inject(this);
        checkNotNull(mApplicationContext, "context cannot be null");
        checkNotNull(mSchedulerProvider, "scheduleProvider cannot be null");
        ProductsDbHelper dbHelper = new ProductsDbHelper(mApplicationContext);
        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        mDatabaseHelper = sqlBrite.wrapDatabaseHelper(dbHelper, mSchedulerProvider.io());
        mProductMapperFunction = this::getProduct;
    }


    @NonNull
    private ProductDetail getProduct(@NonNull Cursor c) {
        String itemId = c.getString(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_ENTRY_ID));
        String title = c.getString(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_TITLE));
        String description =
                c.getString(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_DESCRIPTION));
        String imageUrl =
                c.getString(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_IMAGE));
        String price =
                c.getString(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_PRICE));
        boolean completed =
                c.getInt(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_COMPLETED)) == 1;
        return new ProductDetail(title, description, itemId, imageUrl, price, completed);
    }


    @Override
    public Observable<List<ProductDetail>> getProducts(int startOffSet) {
        String[] projection = {
                ProductEntry.COLUMN_NAME_ENTRY_ID,
                ProductEntry.COLUMN_NAME_TITLE,
                ProductEntry.COLUMN_NAME_DESCRIPTION,
                ProductEntry.COLUMN_NAME_IMAGE,
                ProductEntry.COLUMN_NAME_PRICE,
                ProductEntry.COLUMN_NAME_COMPLETED
        };
        String sql = String.format("SELECT %s FROM %s WHERE ", TextUtils.join(",", projection), ProductEntry.TABLE_NAME);
        return mDatabaseHelper.createQuery(ProductEntry.TABLE_NAME, sql)
                .mapToList(mProductMapperFunction);
    }

    @Override
    public Observable<ProductDetail> getProduct(@NonNull String taskId) {
        String[] projection = {
                ProductEntry.COLUMN_NAME_ENTRY_ID,
                ProductEntry.COLUMN_NAME_TITLE,
                ProductEntry.COLUMN_NAME_DESCRIPTION,
                ProductEntry.COLUMN_NAME_IMAGE,
                ProductEntry.COLUMN_NAME_PRICE,
                ProductEntry.COLUMN_NAME_COMPLETED
        };
        String sql = String.format("SELECT %s FROM %s WHERE %s LIKE ?",
                TextUtils.join(",", projection), ProductEntry.TABLE_NAME, ProductEntry.COLUMN_NAME_ENTRY_ID);
        return mDatabaseHelper.createQuery(ProductEntry.TABLE_NAME, sql, taskId)
                .mapToOneOrDefault(mProductMapperFunction, null);
    }

    @Override
    public void refreshProducts() {
        //TODO: implement database properly
    }

    @Override
    public void saveProducts(@NonNull List<ProductDetail> productDetails) {
        checkNotNull(productDetails);
        for (ProductDetail productDetail : productDetails) {
            ContentValues values = new ContentValues();
            values.put(ProductEntry.COLUMN_NAME_ENTRY_ID, productDetail.getId());
            values.put(ProductEntry.COLUMN_NAME_TITLE, productDetail.getName());
            values.put(ProductEntry.COLUMN_NAME_DESCRIPTION, productDetail.getDescription());
            values.put(ProductEntry.COLUMN_NAME_COMPLETED, productDetail.isCompleted());
            values.put(ProductEntry.COLUMN_NAME_PRICE, productDetail.getPrice());
            values.put(ProductEntry.COLUMN_NAME_IMAGE, productDetail.getImageUrl());
            mDatabaseHelper.insert(ProductEntry.TABLE_NAME, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

}
