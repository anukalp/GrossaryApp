package com.rxandroid.redmarttask.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.rxandroid.redmarttask.R;
import com.rxandroid.redmarttask.listing.ProductListActivity;
import com.rxandroid.redmarttask.util.ActivityUtils;
import com.rxandroid.redmarttask.util.AppConstants;

/**
 * An activity representing a single Product detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ProductListActivity}.
 */
public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            String productId = getIntent().getStringExtra(ProductDetailFragment.ARG_ITEM_ID);
            String itemUrl = getIntent().getStringExtra(ProductDetailFragment.ARG_ITEM_URL);
            ProductDetailFragment detailFragment = ProductDetailFragment.newInstance(productId, itemUrl);
            detailFragment.setPresenter(new ProductDetailPresenter(productId, detailFragment));
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), detailFragment, R.id.product_detail_container, AppConstants.DETAIL_CONTACTS_TAG);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, ProductListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
