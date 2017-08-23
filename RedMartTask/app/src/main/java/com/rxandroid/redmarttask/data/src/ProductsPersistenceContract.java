package com.rxandroid.redmarttask.data.src;

/**
 * Created by anukalp on 23/8/17.
 */

import android.provider.BaseColumns;

/**
 * The contract used for the db to save the Products locally.
 */
public final class ProductsPersistenceContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private ProductsPersistenceContract() {}

    /* Inner class that defines the table contents */
    public static abstract class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "Product";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_COMPLETED = "completed";
    }
}
