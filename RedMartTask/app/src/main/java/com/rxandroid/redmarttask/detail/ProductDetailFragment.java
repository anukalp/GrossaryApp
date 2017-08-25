package com.rxandroid.redmarttask.detail;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
    private ImageView mImageView;
    private Context mContext;


    public static ProductDetailFragment newInstance(String productId, String itemUrl) {
        Bundle arguments = new Bundle();
        arguments.putString(ProductDetailFragment.ARG_ITEM_ID, productId);
        arguments.putString(ProductDetailFragment.ARG_ITEM_URL, itemUrl);
        ProductDetailFragment detailFragment = new ProductDetailFragment();
        detailFragment.setArguments(arguments);
        return detailFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context.getApplicationContext();
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
        productId = getArguments().getString(ProductDetailFragment.ARG_ITEM_ID);
        imageUrl = getArguments().getString(ProductDetailFragment.ARG_ITEM_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_detail, container, false);
        appBarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.toolbar_layout);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.detail_toolbar);
        if(getActivity() instanceof ProductDetailActivity) {
            ((ProductDetailActivity) getActivity()).setSupportActionBar(toolbar);
        }
        mImageView = (ImageView) rootView.findViewById(R.id.product_img);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener((view) ->
                Snackbar.make(view, "Sorry this is beta version checkout is still under development", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());

        Glide.with(mContext).load(imageUrl)
                .centerCrop()
                .into(mImageView);
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
            Glide.with(mContext).load(imageUrl)
                    .centerCrop()
                    .into(mImageView);
        }
    }
}
