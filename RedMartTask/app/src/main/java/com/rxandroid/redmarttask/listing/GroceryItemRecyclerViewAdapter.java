package com.rxandroid.redmarttask.listing;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rxandroid.redmarttask.R;
import com.rxandroid.redmarttask.data.ProductDetail;
import com.rxandroid.redmarttask.util.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anukalp on 22/8/17.
 */

public class GroceryItemRecyclerViewAdapter
        extends RecyclerView.Adapter<GroceryItemRecyclerViewAdapter.ViewHolder> {

    private List<ProductDetail> mProducts = new ArrayList<>();
    private final GroceryItemClickListener listener;

    public GroceryItemRecyclerViewAdapter(List<ProductDetail> items, GroceryItemClickListener listener) {
        if(null != items) {
            mProducts = items;
        }
        this.listener = listener;
    }

    public void addProducts(List<ProductDetail> products) {
        this.mProducts.addAll(products);
    }

    public List<ProductDetail> getProducts() {
        return mProducts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ProductDetail productItem = mProducts.get(position);
        holder.mIdView.setText(productItem.getTitleForList());
        holder.mWeight.setText(productItem.getmWeight());
        if(productItem.getPrice().getPromoPrice() == 0) {
            holder.priceView.setText(ActivityUtils.roundOffDouble(productItem.getPrice().getPrice()));
            holder.discountTextView.setVisibility(View.GONE);
        } else {
            holder.priceView.setText(ActivityUtils.roundOffDouble(productItem.getPrice().getPromoPrice()));
            holder.discountTextView.setText(ActivityUtils.roundOffDouble(productItem.getPrice().getPrice()));
        }

        Glide.with(holder.mView.getContext()).load(productItem.getImageUrl())
                .centerCrop()
                .into(holder.imageView);

        holder.mView.setOnClickListener((v) -> listener.listItemClicked(v.getContext(), productItem.getId(), productItem.getImageUrl()));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public interface GroceryItemClickListener {
        void listItemClicked(Context context, String id, String productId);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final ImageView imageView;
        private final TextView priceView;
        private final TextView discountTextView;
        private final TextView mWeight;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mWeight = (TextView) view.findViewById(R.id.weight);
            imageView = (ImageView) view.findViewById(R.id.product_img);
            priceView = (TextView) view.findViewById(R.id.price);
            discountTextView = (TextView) view.findViewById(R.id.discountText);
            discountTextView.setPaintFlags(discountTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }
}