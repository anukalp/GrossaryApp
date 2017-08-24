package com.rxandroid.redmarttask.detail;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rxandroid.redmarttask.listing.ProductListActivity;
import com.rxandroid.redmarttask.R;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * A fragment representing a single Product detail screen.
 * This fragment is either contained in a {@link ProductListActivity}
 * in two-pane mode (on tablets) or a {@link ProductDetailActivity}
 * on handsets.
 */
public class ProductDetailFragment extends Fragment implements ProductDetailContract.View {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "product-id";
    public static final String ARG_ITEM_URL = "product-img";

    /**
     * The dummy content this fragment is presenting.
     */
    private String productId;
    private ProductDetailContract.Presenter mPresenter;
    private TextView productDescription;
    private Toolbar toolbar;
    private CollapsingToolbarLayout appBarLayout;
    private String imageUrl;


    public static ProductDetailFragment newInstance(String productId, String itemUrl) {
        Bundle arguments = new Bundle();
        arguments.putString(ProductDetailFragment.ARG_ITEM_ID, productId);
        arguments.putString(ProductDetailFragment.ARG_ITEM_URL, itemUrl);
        ProductDetailFragment detailFragment = new ProductDetailFragment();
        detailFragment.setArguments(arguments);
        return detailFragment;
    }

    @Override
    public void setPresenter(@NonNull ProductDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this.getActivity();
        appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        productId = getArguments().getString(ProductDetailFragment.ARG_ITEM_ID);
        imageUrl = getArguments().getString(ProductDetailFragment.ARG_ITEM_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_detail, container, false);
        productDescription = (TextView) rootView.findViewById(R.id.product_detail);
        return rootView;
    }

    @Override
    public void hideTitle() {
        appBarLayout.setTitle(getString(R.string.title_product_detail));
    }

    @Override
    public void showTitle(String title) {
        appBarLayout.setTitle(title);
    }

    @Override
    public void hideDescription() {

    }

    @Override
    public void showDescription(String description) {
        productDescription.setText(description);
    }

    @Override
    public void showCompletionStatus(boolean completed) {

    }

    @Override
    public void setLoadingIndicator(boolean b) {

    }

    @Override
    public void showMissingProduct() {

    }

    @Override
    public void setImage(String imgUrl) {
        if(null == imageUrl) {
            imageUrl = imgUrl;
            Activity activity = this.getActivity();
            ImageView imageView = (ImageView) activity.findViewById(R.id.product_img);
            Glide.with(activity.getApplicationContext()).load(imageUrl)
                    .centerCrop()
                    .into(imageView);
        }
    }
}
