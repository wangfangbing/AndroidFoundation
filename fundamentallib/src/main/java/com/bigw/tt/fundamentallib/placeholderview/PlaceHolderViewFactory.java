package com.bigw.tt.fundamentallib.placeholderview;

import android.view.ViewStub;

import com.bigw.tt.fundamentallib.placeholderview.PlaceHolderView;

/**
 * Created by bigw on 26/12/2017.
 */

public interface PlaceHolderViewFactory {

    PlaceHolderView createPlaceHolderView(ViewStub viewStub);
}
