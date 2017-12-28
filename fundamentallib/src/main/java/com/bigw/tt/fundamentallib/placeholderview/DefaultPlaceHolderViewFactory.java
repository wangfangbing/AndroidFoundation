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

public class DefaultErrorPlaceHolderViewFactory implements PlaceHolderViewFactory {

    @Override
    public PlaceHolderView createPlaceHolderView(ViewStub viewStub) {
        return new ErrorPlaceHolderView(viewStub);
    }

    private static class ErrorPlaceHolderView extends PlaceHolderView {
        public ErrorPlaceHolderView(ViewStub viewStub) {
            super(viewStub);
        }

        @Override
        public void show(int drawableRes, int stringRes) {
            Context context = getView().getContext();
            Drawable drawable = drawableRes > 0 ? context.getResources().getDrawable(drawableRes) : null;
            CharSequence cs = stringRes > 0 ? context.getString(stringRes) : null;
            show(drawable, cs);
        }

        @Override
        public void show(Drawable drawable, CharSequence cs) {
            ImageView imageView = getView().findViewById(R.id.image);
            if (drawable == null) {
                imageView.setVisibility(View.GONE);
            } else {
                imageView.setImageDrawable(drawable);
                imageView.setVisibility(View.VISIBLE);
            }

            TextView textView = getView().findViewById(R.id.text);
            if (TextUtils.isEmpty(cs)) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setText(cs);
                textView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected int getLayoutRes() {
            return R.layout.layout_place_holder_view;
        }
    }
}
