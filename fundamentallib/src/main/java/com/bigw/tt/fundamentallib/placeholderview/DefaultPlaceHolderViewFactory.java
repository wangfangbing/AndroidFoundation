package com.bigw.tt.fundamentallib.placeholderview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigw.tt.fundamentallib.R;

/**
 * Created by bigw on 27/12/2017.
 */

public class DefaultPlaceHolderViewFactory implements PlaceHolderViewFactory {

    @Override
    public PlaceHolderView createPlaceHolderView(ViewStub viewStub) {
        return new ErrorPlaceHolderView(viewStub);
    }

    private static class ErrorPlaceHolderView extends PlaceHolderView {
        public ErrorPlaceHolderView(ViewStub viewStub) {
            super(viewStub);
        }

        @Override
        public void set(Drawable drawable, CharSequence cs, View.OnClickListener listener) {
            ImageView imageView = getView().findViewById(R.id.image);
            if (drawable == null) {
                imageView.setVisibility(View.GONE);
            } else {
                imageView.setImageDrawable(drawable);
                imageView.setOnClickListener(listener);
                imageView.setVisibility(View.VISIBLE);
            }

            TextView textView = getView().findViewById(R.id.text);
            if (TextUtils.isEmpty(cs)) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setText(cs);
                textView.setOnClickListener(listener);
                textView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.layout_place_holder_view;
        }
    }
}
