package com.rxandroid.redmarttask.util;

/**
 * Created by anukalp on 22/8/17.
 */

public class AppConstants {

    public static final int MAX_ITEMS_PER_REQUEST = 20;
    public static final String EDIT_CONTACTS_TAG = "edit-tag";
    public static final String DETAIL_CONTACTS_TAG = "details-tag";
    public static final String BASE_URL = "https://api.redmart.com";
    public static final String IMAGE_BASE_URL = "http://media.redmart.com/newmedia/200p";

    public static final long TIMEOUT = 20;
    public static final String CONTENT_ENCODING ="Content-Encoding";
    public static final String GZIP ="gzip";

    public class API_CONSTANTS {

        public static final String CONTENT_TYPE_APPLICATION_JSON = "ContentType: application/json";
        public static final String REQUEST_PRODUCTS = "/v1.5.8/catalog/search";
        public static final String PRODUCT_ID = "product_id";
        public static final String REQUEST_PRODUCT = "/v1.5.8/catalog/products/{product_id}";
        public static final String PAGE_NO = "page";
        public static final String PAGE_SIZE = "pageSize";

    }
}
