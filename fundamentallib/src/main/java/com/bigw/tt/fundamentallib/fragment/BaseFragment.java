package com.bigw.tt.fundamentallib.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigw.tt.fundamentallib.placeholderview.ContentViewSwitcher;
import com.bigw.tt.fundamentallib.placeholderview.DefaultPlaceHolderViewFactory;
import com.bigw.tt.fundamentallib.placeholderview.PlaceHolderViewFactory;
import com.bigw.tt.fundamentallib.widgets.SwipeRefreshLayoutProxy;

/**
 * Created by bigw on 25/12/2017.
 *
 *
 *
 */
public abstract class BaseFragment extends Fragment {

    private SwipeRefreshLayoutProxy mSwipeRefreshLayoutProxy;
    private ContentViewSwitcher mContentViewSwitcher;

    private PlaceHolderViewFactory mErrorPlaceHolderViewFactory;
    private PlaceHolderViewFactory mEmptyPlaceHolderViewFactory;

    public void setContentViewSwitcher(ContentViewSwitcher switcher) {
        this.mContentViewSwitcher = switcher;
    }

    public void setSwipeRefreshLayout(@NonNull SwipeRefreshLayout refreshLayout) {
        if (refreshLayout != null) {
            mSwipeRefreshLayoutProxy = new SwipeRefreshLayoutProxy(refreshLayout);
        }
    }

    public PlaceHolderViewFactory getErrorPlaceHolderViewFactory() {
        return mErrorPlaceHolderViewFactory;
    }

    public void setErrorPlaceHolderViewFactory(PlaceHolderViewFactory mErrorPlaceHolderViewFactory) {
        this.mErrorPlaceHolderViewFactory = mErrorPlaceHolderViewFactory;
    }

    public PlaceHolderViewFactory getEmptyPlaceHolderViewFactory() {
        return mEmptyPlaceHolderViewFactory;
    }

    public void setEmptyPlaceHolderViewFactory(PlaceHolderViewFactory mEmptyPlaceHolderViewFactory) {
        this.mEmptyPlaceHolderViewFactory = mEmptyPlaceHolderViewFactory;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        if (mSwipeRefreshLayoutProxy != null) {
            return mSwipeRefreshLayoutProxy.getRefreshLayout();
        }
        return null;
    }

    public ContentViewSwitcher getContentViewSwitcher() {
        return this.mContentViewSwitcher;
    }

    public void showSwipeRefreshLayout() {
        if (mSwipeRefreshLayoutProxy != null) {
            mSwipeRefreshLayoutProxy.showRefreshLayout();
        }
    }

    public void hideSwipeRefreshLayout() {
        if (mSwipeRefreshLayoutProxy != null) {
            mSwipeRefreshLayoutProxy.hideRefreshLayout();
        }
    }

    public void switchToContentView() {
        if (getContentViewSwitcher() != null) {
            getContentViewSwitcher().switchToContentView();
        }
    }

    public void switchToEmptyView(@DrawableRes int drawableRes, @StringRes int stringRes) {
        if (getContentViewSwitcher() != null) {
            getContentViewSwitcher().switchToEmptyView(drawableRes, stringRes);
        }
    }

    public void switchToEmptyView(Drawable drawable, CharSequence charSequence) {
        if (getContentViewSwitcher() != null) {
            getContentViewSwitcher().switchToEmptyView(drawable, charSequence);
        }
    }

    public void switchToErrorView(@DrawableRes int drawableRes, @StringRes int stringRes) {
        if (getContentViewSwitcher() != null) {
            getContentViewSwitcher().switchToErrorView(drawableRes, stringRes);
        }
    }

    public void switchToErrorView(Drawable drawable, CharSequence charSequence) {
        if (getContentViewSwitcher() != null) {
            getContentViewSwitcher().switchToErrorView(drawable, charSequence);
        }
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result;
        View customView = onCreateFragmentView(inflater, savedInstanceState);

        if (customView == null) {
            return null;
        }

        if (getSwipeRefreshLayout() == null) {
            SwipeRefreshLayout swipeRefreshLayout = createSwipeRefreshLayout(getContext());
            setSwipeRefreshLayout(swipeRefreshLayout);
            result = swipeRefreshLayout;
        } else {
            result = customView;
        }
        setupSwipeRefreshLayout();

        if (getContentViewSwitcher() == null) {
            ContentViewSwitcher contentViewSwitcher = new ContentViewSwitcher(getContext());
            setContentViewSwitcher(contentViewSwitcher);
            setupContentViewSwitcher(getSwipeRefreshLayout(), customView);
        } else {
            View contentView = null;
            ContentViewSwitcher contentViewSwitcher = getContentViewSwitcher();
            if (contentViewSwitcher.getContentView() == null) {
                contentView = contentViewSwitcher.getFirstChildView();
            }
            /**
             * it has a parent already
             */
            setupContentViewSwitcher(null, contentView);
        }
        return result;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mSwipeRefreshLayoutProxy != null) {
            mSwipeRefreshLayoutProxy.clear();
        }
    }

    @Override
    public void onDestroyView() {
        mSwipeRefreshLayoutProxy = null;
        mContentViewSwitcher = null;
        mEmptyPlaceHolderViewFactory = null;
        mErrorPlaceHolderViewFactory = null;
        super.onDestroyView();
    }

    protected abstract View onCreateFragmentView(LayoutInflater inflater, @Nullable Bundle savedInstanceState);

    protected void onErrorViewClicked(View view) {
        //do nothing
    }

    protected void onRefreshFragment() {
        //do nothing
    }

    protected SwipeRefreshLayout createSwipeRefreshLayout(Context context) {
        SwipeRefreshLayout refreshLayout = new SwipeRefreshLayout(context);
        refreshLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return refreshLayout;
    }

    private void setupSwipeRefreshLayout() {
        SwipeRefreshLayout swipeRefreshLayout = getSwipeRefreshLayout();
        if (swipeRefreshLayout.getLayoutParams() == null) {
            swipeRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshFragment();
            }
        });
    }

    private void setupContentViewSwitcher(ViewGroup parent, View contentView) {
        ContentViewSwitcher contentViewSwitcher = getContentViewSwitcher();
        if (parent != null) {
            contentViewSwitcher.setParent(parent);
        }
        if (contentView != null) {
            contentViewSwitcher.setContentView(contentView);
        }
        if (contentViewSwitcher.getErrorViewClickListener() == null) {
            contentViewSwitcher.setErrorViewClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onErrorViewClicked(v);
                }
            });
        }
        if (getEmptyPlaceHolderViewFactory() == null) {
            setEmptyPlaceHolderViewFactory(new DefaultPlaceHolderViewFactory());
        }
        contentViewSwitcher.setEmptyPlaceHolderViewFactory(getEmptyPlaceHolderViewFactory());

        if (getErrorPlaceHolderViewFactory() == null) {
            setErrorPlaceHolderViewFactory(new DefaultPlaceHolderViewFactory());
        }
        contentViewSwitcher.setErrorPlaceHolderViewFactory(getErrorPlaceHolderViewFactory());
    }

}
