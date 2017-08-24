package com.rxandroid.redmarttask.listing;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rxandroid.redmarttask.util.ActivityUtils;
import com.rxandroid.redmarttask.util.AppConstants;

import dagger.internal.Preconditions;

/**
 * Created by anukalp on 22/8/17.
 */

public abstract class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    private final int maxItemsPerRequest;
    private final LinearLayoutManager layoutManager;
    private int page = 0;
    private int visibleThreshold = 5;
    private boolean loading = false;

    public InfiniteScrollListener(int maxItemsPerRequest, LinearLayoutManager layoutManager) {
        this(maxItemsPerRequest, layoutManager, 5);
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    /**
     * Initializes InfiniteScrollListener, which can be added
     * to RecyclerView with addOnScrollListener method
     *
     * @param maxItemsPerRequest Max items to be loaded in a single request.
     * @param layoutManager      LinearLayoutManager created in the Activity.
     */
    public InfiniteScrollListener(int maxItemsPerRequest, LinearLayoutManager layoutManager, int visibleThreshold) {
        ActivityUtils.checkNotNegative(maxItemsPerRequest, "maxItemsPerRequest <= 0");
        Preconditions.checkNotNull(layoutManager, "layoutManager == null");
        this.maxItemsPerRequest = maxItemsPerRequest;
        this.layoutManager = layoutManager;
        this.visibleThreshold = visibleThreshold;
    }

    /**
     * Callback method to be invoked when the RecyclerView has been scrolled
     *
     * @param recyclerView The RecyclerView which scrolled.
     * @param dx           The amount of horizontal scroll.
     * @param dy           The amount of vertical scroll.
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (canLoadMoreItems()) {
            loading = true;
            final int pageNo = layoutManager.getItemCount() / AppConstants.MAX_ITEMS_PER_REQUEST;
            onScrolledToEnd(pageNo);
        }
    }

    /**
     * Refreshes RecyclerView by setting new adapter,
     * calling invalidate method and scrolling to given position
     *
     * @param view     RecyclerView to be refreshed
     * @param adapter  adapter with new list of items to be loaded
     * @param position position to which RecyclerView will be scrolled
     */
    protected void refreshView(RecyclerView view, RecyclerView.Adapter adapter, int position) {
        view.setAdapter(adapter);
        view.invalidate();
        view.scrollToPosition(position);
    }

    /**
     * Checks if more items can be loaded to the RecyclerView
     *
     * @return boolean Returns true if can load more items or false if not.
     */
    protected boolean canLoadMoreItems() {
        if (loading) {
            return false;
        }
        final int totalItemsCount = layoutManager.getItemCount();
        final int pastVisibleItemsCount = layoutManager.findLastVisibleItemPosition();
        final boolean lastItemShown = (pastVisibleItemsCount + visibleThreshold) >= totalItemsCount;
        return lastItemShown && totalItemsCount >= maxItemsPerRequest;
    }

    /**
     * Callback method to be invoked when the RecyclerView has been scrolled to the end
     *
     * @param startOffset startOffset to load More Items
     */
    public abstract void onScrolledToEnd(final int startOffset);
}
