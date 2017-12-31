package com.bigw.tt.fundamentallib.placeholderview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.ViewStubCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.bigw.tt.fundamentallib.R;

/**
 * Created by bigw on 26/12/2017.
 */

public class ContentViewSwitcher extends FrameLayout {

    private View mContentView;
    private PlaceHolderViewFactory mEmptyPlaceHolderViewFactory;
    private PlaceHolderViewFactory mErrorPlaceHolderViewFactory;
    private PlaceHolderView mEmptyPlaceHolderView;
    private PlaceHolderView mErrorPlaceHolderView;

    private View.OnClickListener mErrorViewClickListener;
    private View.OnClickListener mEmptyViewClickListener;

    public ContentViewSwitcher(@NonNull Context context) {
        super(context);
        initView();
    }

    public ContentViewSwitcher(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public View getContentView() {
        return this.mContentView;
    }

    public View getFirstChildView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (!(view instanceof ViewStub || view instanceof ViewStubCompat)) {
                return view;
            }
        }
        return null;
    }

    public void setErrorViewClickListener(View.OnClickListener listener) {
        this.mErrorViewClickListener = listener;
    }

    public View.OnClickListener getErrorViewClickListener() {
        return this.mErrorViewClickListener;
    }

    public void setEmptyViewClickListener(View.OnClickListener listener) {
        this.mEmptyViewClickListener = listener;
    }

    public View.OnClickListener getEmptyViewClickListener() {
        return this.mEmptyViewClickListener;
    }

    public void setParent(@NonNull ViewGroup parent) {
        if (getParent() != null) {
            ViewGroup parentView = (ViewGroup) getParent();
            parentView.removeView(this);
        }
        parent.addView(this);
    }

    public void setContentView(View contentView) {
        if (contentView.getParent() != null) {
            ViewGroup oldParentView = (ViewGroup) contentView.getParent();
            oldParentView.removeView(contentView);
        }
        this.mContentView = contentView;
        addView(this.mContentView);
    }

    public void setErrorPlaceHolderViewFactory(PlaceHolderViewFactory factory) {
        this.mEmptyPlaceHolderViewFactory = factory;
    }

    public void setEmptyPlaceHolderViewFactory(PlaceHolderViewFactory factory) {
        this.mErrorPlaceHolderViewFactory = factory;
    }

    public void switchToContentView() {
        if (mContentView != null) {
            mContentView.setVisibility(View.VISIBLE);
        }
        if (mErrorPlaceHolderView != null) {
            mErrorPlaceHolderView.getView().setVisibility(View.GONE);
        }
        if (mEmptyPlaceHolderView != null) {
            mEmptyPlaceHolderView.getView().setVisibility(View.GONE);
        }
    }

    public void switchToEmptyView() {
        switchToEmptyView(0, 0);
    }

    public void switchToEmptyView(@DrawableRes int drawableRes, @StringRes int emptyTips) {
        Drawable drawable = drawableRes > 0 ? getContext().getResources().getDrawable(drawableRes) : null;
        CharSequence cs = emptyTips > 0 ? getContext().getString(emptyTips) : null;
        switchToEmptyView(drawable, cs);
    }

    public void switchToEmptyView(Drawable drawable, CharSequence cs) {
        if (mContentView != null) {
            mContentView.setVisibility(View.GONE);
        }
        if (mErrorPlaceHolderView != null) {
            mErrorPlaceHolderView.getView().setVisibility(View.GONE);
        }
        if (mEmptyPlaceHolderView == null) {
            mEmptyPlaceHolderView = createPlaceHolderView(mEmptyPlaceHolderViewFactory, R.id.id_empty_view_stub);
        }
        if (mEmptyPlaceHolderView != null) {
            mEmptyPlaceHolderView.set(drawable, cs, mEmptyViewClickListener);
            mEmptyPlaceHolderView.getView().setVisibility(View.VISIBLE);
        }
    }

    public void switchToErrorView() {
        switchToErrorView(0, 0);
    }

    public void switchToErrorView(@DrawableRes int drawableRes, @StringRes int errorTips) {
        Drawable drawable = drawableRes > 0 ? getContext().getResources().getDrawable(drawableRes) : null;
        CharSequence cs = errorTips > 0 ? getContext().getString(errorTips) : null;
        switchToErrorView(drawable, cs);
    }

    public void switchToErrorView(Drawable drawable, CharSequence cs) {
        if (mContentView != null) {
            mContentView.setVisibility(View.GONE);
        }
        if (mEmptyPlaceHolderView != null) {
            mEmptyPlaceHolderView.getView().setVisibility(View.GONE);
        }
        if (mErrorPlaceHolderView == null) {
            mErrorPlaceHolderView = createPlaceHolderView(mErrorPlaceHolderViewFactory, R.id.id_error_view_stub);
        }
        if (mErrorPlaceHolderView != null) {
            mErrorPlaceHolderView.set(drawable, cs, mErrorViewClickListener);
            mErrorPlaceHolderView.getView().setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        ViewStub emptyViewStub = createViewStub(R.id.id_empty_view_stub);
        addView(emptyViewStub);
        ViewStub errorViewStub = createViewStub(R.id.id_error_view_stub);
        addView(errorViewStub);
        ViewStub contentViewStub = createViewStub(R.id.id_content_view_stub);
        addView(contentViewStub);
    }

    @VisibleForTesting
    ViewStub createViewStub(@IdRes int id) {
        ViewStub viewStub = new ViewStub(getContext());
        viewStub.setId(id);
        return viewStub;
    }

    @VisibleForTesting
    PlaceHolderView createPlaceHolderView(PlaceHolderViewFactory factory, @IdRes int viewStubId) {
        if (factory != null) {
            ViewStub viewStub = findViewById(viewStubId);
            if (viewStub != null) {
                return factory.createPlaceHolderView(viewStub);
            }
        }
        return null;
    }
}
