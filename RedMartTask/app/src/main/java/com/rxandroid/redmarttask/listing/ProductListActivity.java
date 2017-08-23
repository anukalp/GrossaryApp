package com.rxandroid.redmarttask.listing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.rxandroid.redmarttask.R;
import com.rxandroid.redmarttask.data.ProductDetail;
import com.rxandroid.redmarttask.detail.ProductDetailActivity;
import com.rxandroid.redmarttask.detail.ProductDetailFragment;
import com.rxandroid.redmarttask.util.AppConstants;

import java.util.List;

/**
 * An activity representing a list of Products. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ProductDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ProductListActivity extends AppCompatActivity implements GroceryItemRecyclerViewAdapter.GroceryItemClickListener, ProductListingContract.View {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean isMultiPane;
    private GroceryItemRecyclerViewAdapter groceryItemAdapter;
    private ProductListingContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        mPresenter = new ProductListingPresenter(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener((view) ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
        );

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.product_list);
        setupRecyclerView(recyclerView);

        if (findViewById(R.id.product_detail_container) != null) {
            isMultiPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        groceryItemAdapter = new GroceryItemRecyclerViewAdapter(null, this);
        recyclerView.setAdapter(groceryItemAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        InfiniteScrollListener scrollListener = createInfiniteScrollListener(layoutManager);
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);
    }

    @NonNull
    private InfiniteScrollListener createInfiniteScrollListener(LinearLayoutManager layoutManager) {
        return new InfiniteScrollListener(AppConstants.MAX_ITEMS_PER_REQUEST, layoutManager) {
            @Override
            public void onScrolledToEnd(final int startOffSet) {
                mPresenter.loadMore(startOffSet);
            }
        };
    }


    @Override
    public void listItemClicked(Context context, String itemId) {
        if (isMultiPane) {
            ProductDetailFragment fragment = new ProductDetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.product_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra(ProductDetailFragment.ARG_ITEM_ID, itemId);
            context.startActivity(intent);
        }
    }

    @Override
    public void setPresenter(ProductListingContract.Presenter presenter) {
        //TODO: create fragment for listing  mPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean showLoader) {

    }

    @Override
    public void setProductList(List<ProductDetail> productDetailList) {
        int startCount = groceryItemAdapter.getItemCount();
        groceryItemAdapter.addProducts(productDetailList);
        groceryItemAdapter.notifyItemRangeChanged(startCount, productDetailList.size());
    }

    @Override
    public void showLoadingProductsError() {

    }
}