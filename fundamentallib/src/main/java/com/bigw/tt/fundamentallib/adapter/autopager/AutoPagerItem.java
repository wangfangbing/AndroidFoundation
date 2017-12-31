package com.bigw.tt.fundamentallib.adapter.autopager;

import android.support.annotation.IntDef;

/**
 * Created by bigw on 29/12/2017.
 */
public class AutoPagerItem {

    public static final int STATE_PENDING_LOAD = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_END = 2;
    public static final int STATE_ERROR = 3;

    @IntDef({STATE_PENDING_LOAD, STATE_LOADING, STATE_END, STATE_ERROR})
    public @interface FooterState {}

    private static final int DEFAULT_PAGE_COUNT = 20;
    private int mPage = 1;
    private int mPageCount = DEFAULT_PAGE_COUNT;
    private boolean mShouldShowEndView = true;

    private @FooterState int mFooterState = STATE_PENDING_LOAD;

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        this.mPage = page;
    }

    public int getPageCount() {
        return mPageCount;
    }

    public void setPageCount(int pageCount) {
        this.mPageCount = pageCount;
    }

    public void setFooterState(@FooterState int footerState) {
        this.mFooterState = footerState;
    }

    public @FooterState int getFooterState() {
        return this.mFooterState;
    }

    public boolean shouldShowEndView() {
        return mShouldShowEndView;
    }

    public void setShouldShowEndView(boolean shouldShow) {
        this.mShouldShowEndView = shouldShow;
    }

    public void resetState() {
        mPage = 1;
        mFooterState = STATE_PENDING_LOAD;
    }
}
