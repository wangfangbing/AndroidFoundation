package com.bigw.tt.foundation.basefragment;

import android.content.Intent;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bigw.tt.fundamentallib.placeholderview.ContentViewSwitcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by bigw on 27/12/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BaseFragmentExampleActivityTest {

    @Rule
    public ActivityTestRule<BaseFragmentExampleActivity> mActivityRule = new ActivityTestRule<>(BaseFragmentExampleActivity.class);

    @Before
    public void setUp() throws Exception {
        mActivityRule.launchActivity(new Intent());
    }

    @Test @UiThreadTest
    public void defaultContentViewSwitcher_setParent() {
        BaseFragmentExampleActivity activity = mActivityRule.getActivity();
        ContentViewSwitcher contentViewSwitcher = activity.getContentViewSwitcher();

        assertTrue(contentViewSwitcher == null);



    }
}