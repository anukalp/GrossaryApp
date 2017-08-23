package com.rxandroid.redmarttask.listing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rxandroid.redmarttask.R;
import com.rxandroid.redmarttask.data.ProductDetail;

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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ProductDetail productItem = mProducts.get(position);
        holder.mIdView.setText(productItem.getName());
        holder.mContentView.setText(productItem.getPrice());
        Glide.with(holder.mContentView.getContext()).load(productItem.getImageUrl())
                .centerCrop()
                .into(holder.imageView);

        holder.mView.setOnClickListener((v) -> listener.listItemClicked(v.getContext(), String.valueOf(holder.getItemId())));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public interface GroceryItemClickListener {
        void listItemClicked(Context context, String productId);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            imageView = (ImageView) view.findViewById(R.id.product_img);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}