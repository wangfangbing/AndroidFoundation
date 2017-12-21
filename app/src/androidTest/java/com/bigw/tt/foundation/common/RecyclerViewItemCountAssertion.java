package com.bigw.tt.foundation.common;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by bigw on 21/12/2017.
 */

public class RecyclerViewItemCountAssertion implements ViewAssertion {

    private final Matcher<Integer> matcher;

    public static RecyclerViewItemCountAssertion withItemCount(int expectedCount) {
        return new RecyclerViewItemCountAssertion(is(expectedCount));
    }

    public RecyclerViewItemCountAssertion(Matcher<Integer> matcher) {
        this.matcher = matcher;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw  noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat(recyclerView.getChildCount(), matcher);
        assertThat(adapter.getItemCount(), matcher);
    }
}
