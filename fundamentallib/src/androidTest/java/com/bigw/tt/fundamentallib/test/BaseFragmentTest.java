package com.bigw.tt.fundamentallib.test;

import android.content.Intent;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by bigw on 27/12/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestBaseFragmentActivityTest {

    private DisplayStringListFragment mFragment;

    public ActivityTestRule<TestBaseFragmentActivity> mActivityRule =
            new ActivityTestRule<>(TestBaseFragmentActivity.class);

    @Before
    public void setUp() throws Exception {
        mActivityRule.launchActivity(new Intent());
        mFragment = DisplayStringListFragment.newInstance();
        mActivityRule.getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, mFragment).commit();
    }

    @Test @UiThreadTest
    public void defaultContentViewSwitcher_setParent() {
        assertTrue(mFragment.isAdded());
    }
}