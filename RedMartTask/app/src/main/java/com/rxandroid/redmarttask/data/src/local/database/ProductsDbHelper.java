package com.rxandroid.redmarttask.data.src.local.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anukalp on 23/8/17.
 */

public class ProductsDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "products.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProductsPersistenceContract.ProductEntry.TABLE_NAME + " (" +
                    ProductsPersistenceContract.ProductEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                    ProductsPersistenceContract.ProductEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    ProductsPersistenceContract.ProductEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    ProductsPersistenceContract.ProductEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    ProductsPersistenceContract.ProductEntry.COLUMN_NAME_IMAGE + TEXT_TYPE + COMMA_SEP +
                    ProductsPersistenceContract.ProductEntry.COLUMN_NAME_PRICE + TEXT_TYPE + COMMA_SEP +
                    ProductsPersistenceContract.ProductEntry.COLUMN_NAME_COMPLETED + BOOLEAN_TYPE +
                    " )";

    public ProductsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
