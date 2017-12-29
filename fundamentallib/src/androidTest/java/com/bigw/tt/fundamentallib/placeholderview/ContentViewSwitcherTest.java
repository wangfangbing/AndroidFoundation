package com.bigw.tt.fundamentallib.placeholderview;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
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
        assertEquals(2, contentViewSwitcher.getChildCount());
    }

    @Test @UiThreadTest
    public void checkSetParent() {
        FrameLayout frameLayout = new FrameLayout(mContext);
        TextView textView = new TextView(mContext);
        frameLayout.addView(textView);
        assertEquals(0, frameLayout.indexOfChild(textView));

        ContentViewSwitcher contentViewSwitcher = new ContentViewSwitcher(mContext);
        assertNull(contentViewSwitcher.getLayoutParams());

        contentViewSwitcher.setParent(frameLayout);
        assertEquals(1, frameLayout.indexOfChild(contentViewSwitcher));
        assertNotNull(contentViewSwitcher.getLayoutParams());
    }

    @Test @UiThreadTest
    public void checkSwitchView() {
        ContentViewSwitcher contentViewSwitcher = new ContentViewSwitcher(mContext);
        PlaceHolderViewFactory factory = new DefaultPlaceHolderViewFactory();
        contentViewSwitcher.setErrorPlaceHolderViewFactory(factory);
        contentViewSwitcher.setEmptyPlaceHolderViewFactory(factory);
        TextView contentView = new TextView(mContext);
        contentView.setText("contentView");
        contentViewSwitcher.setContentView(contentView);

        assertEquals(3, contentViewSwitcher.getChildCount());
        contentViewSwitcher.switchToContentView();
        contentViewSwitcher.switchToEmptyView();
        contentViewSwitcher.switchToErrorView();

        contentViewSwitcher.switchToContentView();
        contentViewSwitcher.switchToEmptyView();
        contentViewSwitcher.switchToErrorView();
        assertEquals(3, contentViewSwitcher.getChildCount());
    }

    @Test @UiThreadTest
    public void checkFirstChild() {
        ContentViewSwitcher contentViewSwitcher = new ContentViewSwitcher(mContext);
        assertNull(contentViewSwitcher.getFirstChildView());

        TextView textView = new TextView(mContext);
        contentViewSwitcher.addView(textView);

        ImageView imageView = new ImageView(mContext);
        contentViewSwitcher.addView(imageView);
        assertEquals(textView, contentViewSwitcher.getFirstChildView());
    }
}
