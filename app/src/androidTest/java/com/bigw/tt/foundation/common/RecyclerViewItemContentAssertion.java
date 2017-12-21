package com.bigw.tt.foundation.common;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by bigw on 21/12/2017.
 */

public class RecyclerViewItemContentAssertion implements ViewAssertion {


    public RecyclerViewItemContentAssertion() {
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException !=  null) {
            throw noViewFoundException;
        }
        RecyclerView recyclerView = (RecyclerView) view;
    }
}
