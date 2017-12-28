package com.bigw.tt.fundamentallib.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by bigw on 26/12/2017.
 */

public class ContentViewSwitcher extends FrameLayout {

    public interface OnErrorPlaceHolderViewClickedListener {
        void onErrorPlaceHolderViewClicked();
    }

    public ContentViewSwitcher(@NonNull Context context) {
        super(context);
        addPlaceHolderViewStubs();
    }

    public ContentViewSwitcher(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addPlaceHolderViewStubs();
    }

    public void setOnErrorPlaceHolderViewClickedListener(OnErrorPlaceHolderViewClickedListener listener) {

    }

    public void setParent(@NonNull ViewGroup parent) {
        //TODO add view index
    }

    public void switchToNormalView() {

    }

    public void switchToEmptyView(@DrawableRes int drawableRes, @StringRes int emptyTips) {

    }

    public void switchToErrorView(@DrawableRes int drawableRes, @StringRes int errorTips) {

    }

    private void addPlaceHolderViewStubs() {
//        ViewStub emptyViewStub = new ViewStub(getContext());
//        emptyViewStub.setId(R.id.empty_view_stub_id);
//        emptyViewStub.setInflatedId(R.id.empty_view_stub_inflate_id);
//        addView(emptyViewStub);
//
//        ViewStub errorViewStub = new ViewStub(getContext());
//        errorViewStub.setId(R.id.error_view_stub_id);
//        errorViewStub.setInflatedId(R.id.error_view_stub_inflate_id);
//        addView(errorViewStub);
    }

    private void showContentView() {

    }
}
