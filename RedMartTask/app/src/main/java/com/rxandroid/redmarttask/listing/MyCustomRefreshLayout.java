package com.rxandroid.redmarttask.listing;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by anukalp on 25/8/17.
 */

public class MyCustomRefreshLayout extends SwipeRefreshLayout {
    private int actionBarHeight;
    private int startOffset;

    public MyCustomRefreshLayout(Context context) {
        super(context);
        initActionBarHeight(context);
    }

    private void initActionBarHeight(Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
    }

    public MyCustomRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initActionBarHeight(context);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (startOffset == 0) {
            int totalHeight = getMeasuredHeight() - actionBarHeight;
            startOffset = totalHeight - getProgressCircleDiameter();
            setProgressViewOffset(true, startOffset, totalHeight);
        }
    }
}
