package com.rxandroid.redmarttask.data.src.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.rxandroid.redmarttask.application.RedMartApplication;
import com.rxandroid.redmarttask.data.ProductDetail;
import com.rxandroid.redmarttask.data.src.ProductDataSource;
import com.rxandroid.redmarttask.data.src.local.database.ProductsDbHelper;
import com.rxandroid.redmarttask.data.src.local.database.ProductsPersistenceContract.ProductEntry;
import com.rxandroid.redmarttask.data.src.network.pojo.Pricing;
import com.rxandroid.redmarttask.util.AppConstants;
import com.rxandroid.redmarttask.util.schedulers.BaseSchedulerProvider;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.QueryObservable;
import com.squareup.sqlbrite2.SqlBrite;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

import static com.google.common.base.Preconditions.checkNotNull;

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

    @Inject
    Gson gson;

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
        String weight =
                c.getString(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_WEIGHT));
        boolean completed =
                c.getInt(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_COMPLETED)) == 1;
        return new ProductDetail(title, description, itemId, imageUrl, weight, gson.fromJson(price, Pricing.class), completed);
    }


    @Override
    public Observable<List<ProductDetail>> getProducts(int pageNo) {
        int startOffSet = pageNo * AppConstants.MAX_ITEMS_PER_REQUEST;
        String[] projection = {
                ProductEntry.COLUMN_NAME_ENTRY_ID,
                ProductEntry.COLUMN_NAME_TITLE,
                ProductEntry.COLUMN_NAME_DESCRIPTION,
                ProductEntry.COLUMN_NAME_IMAGE,
                ProductEntry.COLUMN_NAME_PRICE,
                ProductEntry.COLUMN_NAME_WEIGHT,
                ProductEntry.COLUMN_NAME_COMPLETED
        };
        String sql = String.format("SELECT %s FROM %s WHERE " + ProductEntry.COLUMN_NAME_ENTRY_ID + " > %s", TextUtils.join(",", projection), ProductEntry.TABLE_NAME, startOffSet);
        Observable<List<ProductDetail>> productDetails = mDatabaseHelper.createQuery(ProductEntry.TABLE_NAME, sql)
                .mapToList(mProductMapperFunction);
        return productDetails;
    }

    @Override
    public Observable<ProductDetail> getProduct(@NonNull String productId) {
        String[] projection = {
                ProductEntry.COLUMN_NAME_ENTRY_ID,
                ProductEntry.COLUMN_NAME_TITLE,
                ProductEntry.COLUMN_NAME_DESCRIPTION,
                ProductEntry.COLUMN_NAME_IMAGE,
                ProductEntry.COLUMN_NAME_PRICE,
                ProductEntry.COLUMN_NAME_WEIGHT,
                ProductEntry.COLUMN_NAME_COMPLETED
        };
        String sql = String.format("SELECT %s FROM %s WHERE %s LIKE ?",
                TextUtils.join(",", projection), ProductEntry.TABLE_NAME, ProductEntry.COLUMN_NAME_ENTRY_ID);
        return mDatabaseHelper.createQuery(ProductEntry.TABLE_NAME, sql, productId)
                .mapToOneOrDefault(mProductMapperFunction, null);
    }

    @Override
    public void refreshProducts() {
        //TODO: implement database properly
    }

    public void saveProducts(@NonNull List<ProductDetail> productDetails) {
        checkNotNull(productDetails);

        List<List<ProductDetail>> partitions = new LinkedList<>();
        for (int i = 0; i < productDetails.size(); i += AppConstants.MAX_ITEMS_PER_REQUEST) {
            partitions.add(productDetails.subList(i,
                    Math.min(i + AppConstants.MAX_ITEMS_PER_REQUEST, productDetails.size())));
        }
        int startValue = 0;
        //Bulk Insert 20 as per pagination api
        while (startValue < partitions.size()) {
            List<ProductDetail> detailList = partitions.get(startValue++);
            BriteDatabase.Transaction transaction = mDatabaseHelper.newTransaction();
            try {
                for (ProductDetail productDetail : detailList) {
                    ContentValues values = new ContentValues();
                    values.put(ProductEntry.COLUMN_NAME_ENTRY_ID, productDetail.getId());
                    values.put(ProductEntry.COLUMN_NAME_TITLE, productDetail.getName());
                    values.put(ProductEntry.COLUMN_NAME_DESCRIPTION, productDetail.getDescription());
                    values.put(ProductEntry.COLUMN_NAME_COMPLETED, productDetail.isCompleted());
                    values.put(ProductEntry.COLUMN_NAME_PRICE, gson.toJson(productDetail.getPrice()));
                    values.put(ProductEntry.COLUMN_NAME_IMAGE, productDetail.getImageUrl());
                    values.put(ProductEntry.COLUMN_NAME_ADDED, productDetail.getAdded());
                    values.put(ProductEntry.COLUMN_NAME_WEIGHT, productDetail.getmWeight());
                    mDatabaseHelper.insert(ProductEntry.TABLE_NAME, values);
                }
                transaction.markSuccessful();
            } finally {
                transaction.end();
            }
        }

    }

    @Override
    public void addProductsToCart(@NonNull String productId) {

    }

}
