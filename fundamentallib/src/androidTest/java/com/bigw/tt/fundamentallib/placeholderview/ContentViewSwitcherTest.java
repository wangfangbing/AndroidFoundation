package com.bigw.tt.fundamentallib.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;
import android.widget.FrameLayout;

import com.bigw.tt.fundamentallib.placeholderview.ContentViewSwitcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Created by bigw on 28/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class ContentViewSwitcherTest {

    private Context mContext;

    @Before
    public void setUp() throws Exception {
        mContext = InstrumentationRegistry.getContext();
    }

    @Test
    public void checkContext() {
        assertNotNull(mContext);
    }

    @Test @UiThreadTest
    public void checkAddViewStub() {
        ContentViewSwitcher contentViewSwitcher = new ContentViewSwitcher(mContext);
        contentViewSwitcher.createViewStub(0, 0);
    }
}
