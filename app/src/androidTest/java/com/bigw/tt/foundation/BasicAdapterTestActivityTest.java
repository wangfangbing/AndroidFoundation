package com.bigw.tt.foundation;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.bigw.tt.foundation.basicadapter.BasicAdapterTestActivity;
import com.bigw.tt.foundation.common.CustomClick;
import com.bigw.tt.fundamentallib.adapter.BasicAdapter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by bigw on 15/12/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BasicAdapterTestActivityTest {

    @Rule
    public ActivityTestRule<BasicAdapterTestActivity> mActivityRule = new ActivityTestRule<>(BasicAdapterTestActivity.class);

    @Before
    public void setUp() throws Exception {
        mActivityRule.launchActivity(new Intent());
    }

    @Test
    public void onCreate() {
        BasicAdapterTestActivity activity = mActivityRule.getActivity();
        assertNotNull(activity);

        BasicAdapter adapter = activity.getAdapter();
        assertNotNull(adapter);

        RecyclerView recyclerView = activity.getRecyclerView();
        assertNotNull(recyclerView);
    }

    @Test
    public void append() {
        BasicAdapterTestActivity activity = mActivityRule.getActivity();
        onView(withId(R.id.append)).perform(new CustomClick());
        assertEquals(1, activity.getAdapter().getItemCount());
    }

}