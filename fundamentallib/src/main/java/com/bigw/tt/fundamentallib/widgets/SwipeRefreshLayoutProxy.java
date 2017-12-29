package com.bigw.tt.fundamentallib.widgets;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by bigw on 27/12/2017.
 */

public class SwipeRefreshLayoutProxy {

    private static final int HIDE_SWIPE_REFRESH_LAYOUT_INTERVAL = 500; //500ms

    private final SwipeRefreshLayout mSwipeRefreshLayout;

    private long mLastRefreshTime = 0;

    public SwipeRefreshLayoutProxy(SwipeRefreshLayout refreshLayout) {
        this.mSwipeRefreshLayout = refreshLayout;
    }

    public SwipeRefreshLayout getRefreshLayout() {
        return this.mSwipeRefreshLayout;
    }

    public void showRefreshLayout() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.post(mShowSwipeRefreshLayoutTask);
        }
    }

    public void hideRefreshLayout() {
        mSwipeRefreshLayout.removeCallbacks(mShowSwipeRefreshLayoutTask);
        long delta = System.currentTimeMillis() - mLastRefreshTime;
        if (delta < HIDE_SWIPE_REFRESH_LAYOUT_INTERVAL) {
            mSwipeRefreshLayout.postDelayed(mHideSwipeRefreshLayoutTask, HIDE_SWIPE_REFRESH_LAYOUT_INTERVAL - delta);
        } else {
            mSwipeRefreshLayout.post(mHideSwipeRefreshLayoutTask);
        }
    }

    public void clear() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            mSwipeRefreshLayout.destroyDrawingCache();
            mSwipeRefreshLayout.clearAnimation();
        }
    }

    private Runnable mShowSwipeRefreshLayoutTask = new Runnable() {
        @Override
        public void run() {
            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(true);
            }
            mLastRefreshTime = System.currentTimeMillis();
        }
    };

    private Runnable mHideSwipeRefreshLayoutTask = new Runnable() {
        @Override
        public void run() {
            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    };
}
