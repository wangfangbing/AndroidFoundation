package com.bigw.tt.foundation;

import android.content.Intent;
import android.support.test.annotation.UiThreadTest;
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

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.bigw.tt.foundation.common.RecyclerViewItemCountAssertion.withItemCount;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

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

    @Test @UiThreadTest
    public void adapter_appendItem() {
        BasicAdapterTestActivity activity = mActivityRule.getActivity();
        BasicAdapter adapter = activity.getAdapter();
        List dataList = adapter.getDataList();

        assertEquals(0, adapter.getItemCount());

        String s1 = "s1";
        adapter.appendItem(s1);
        assertEquals(1, adapter.getItemCount());
        assertEquals(s1, dataList.get(dataList.size() - 1));

        String s2 = "s2";
        adapter.appendItem(s2);
        assertEquals(2, adapter.getItemCount());
        assertEquals(s2, dataList.get(dataList.size() - 1));
        assertEquals(s1, dataList.get(dataList.size() - 2));
    }

    @Test @UiThreadTest
    public void adapter_insertItem() {
        BasicAdapterTestActivity activity = mActivityRule.getActivity();
        BasicAdapter adapter = activity.getAdapter();
        List dataList = adapter.getDataList();

        assertEquals(0, adapter.getItemCount());

        String s1 = "s1";
        adapter.insertItem(s1, 0);
        assertEquals(1, adapter.getItemCount());
        assertEquals(s1, dataList.get(dataList.size() - 1));

        String s2 = "s2";
        adapter.insertItem(s2, -1);
        assertEquals(2, adapter.getItemCount());
        assertEquals(s2, dataList.get(dataList.size() - 2));
        assertEquals(s1, dataList.get(dataList.size() - 1));

        String s3 = "s3";
        adapter.insertItem(s3, 6); //if greater then size ,then append
        assertEquals(3, adapter.getItemCount());
        assertEquals(s3, dataList.get(dataList.size() - 1));

        String s4 = "s4";
        adapter.insertItem(s4, 1);
        assertEquals(4, adapter.getItemCount());
        assertEquals(s4, dataList.get(adapter.getPosition(s4)));
    }

    @Test @UiThreadTest
    public void adapter_removeItem() {
        BasicAdapterTestActivity activity = mActivityRule.getActivity();
        BasicAdapter adapter = activity.getAdapter();

        String s1 = "s1", s2 = "s2", s3 = "s3";
        adapter.insertItem(s1, 5);
        adapter.insertItem(s2, 1);
        adapter.appendItem(s3);
        assertEquals(3, adapter.getItemCount());

        assertEquals(0, adapter.getPosition(s1));
        assertEquals(1, adapter.getPosition(s2));
        assertEquals(2, adapter.getPosition(s3));

        adapter.removeItem(s2);
        assertEquals(2, adapter.getItemCount());
        assertEquals(0, adapter.getPosition(s1));
        assertEquals(1, adapter.getPosition(s3));
        assertEquals(-1, adapter.getPosition(s2));
    }
}