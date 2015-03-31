package com.wernerapps.ezbongo.StopListing;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class CustomSwipeRefreshLayout extends SwipeRefreshLayout{
    public CustomSwipeRefreshLayout(Context context) {
        super(context);
        setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    public boolean canChildScrollUp() {
        return true;
    }
}
