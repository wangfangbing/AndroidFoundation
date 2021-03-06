package com.bigw.tt.fundamentallib.placeholderview;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.ViewStub;

/**
 * Created by bigw on 26/12/2017.
 */

public abstract class PlaceHolderView {

    private final View mView;

    public PlaceHolderView(@NonNull ViewStub viewStub) {
        viewStub.setLayoutResource(getLayoutRes());
        mView = viewStub.inflate();
    }

    public View getView() {
        return mView;
    }

    public abstract void set(Drawable drawable, CharSequence cs, View.OnClickListener listener);

    protected abstract  @LayoutRes int getLayoutRes();
}
