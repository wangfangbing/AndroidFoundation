package com.bigw.tt.foundation;

import android.content.Intent;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.bigw.tt.foundation.basicadapter.BasicAdapterTestActivity;
import com.bigw.tt.fundamentallib.adapter.BasicAdapter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

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
    public void adapter_appendItems() {
        BasicAdapterTestActivity activity = mActivityRule.getActivity();
        BasicAdapter adapter = activity.getAdapter();
        List dataList = adapter.getDataList();

        adapter.appendItem("s1");
        assertEquals(1, adapter.getItemCount());

        List newDataList = new ArrayList();
        newDataList.add("s2");
        newDataList.add("s3");
        adapter.appendItems(newDataList);
        assertEquals(3, adapter.getItemCount());

        List nullList = null;
        adapter.appendItems(nullList);
        assertEquals(3, adapter.getItemCount());

        List newDataList1 = new ArrayList();
        newDataList1.add("s4");
        newDataList1.add("s5");
        adapter.appendItems(newDataList1);
        assertEquals(5, adapter.getItemCount());
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

        List nullList = null;
        adapter.insertItem(nullList, 0);
        assertEquals(1, adapter.getItemCount());

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
    public void adapter_insertItems() {
        BasicAdapterTestActivity activity = mActivityRule.getActivity();
        BasicAdapter adapter = activity.getAdapter();
        assertEquals(0, adapter.getItemCount());
        List dataList = adapter.getDataList();

        String s1 = "s1";
        String s2 = "s2";
        adapter.insertItem(s1, 0);
        adapter.appendItem(s2);

        List dataList_1 = new ArrayList();
        dataList_1.add("s3");
        dataList_1.add("s4");
        adapter.insertItems(dataList_1, 2);
        assertEquals(4, adapter.getItemCount());
        assertEquals("s3", dataList.get(2));
        assertEquals("s4", dataList.get(3));

        List dataList_2 = new ArrayList();
        dataList_2.add("s5");
        dataList_2.add("s6");
        adapter.insertItems(dataList_2, 0);
        assertEquals(6, adapter.getItemCount());
        assertEquals("s5", dataList.get(0));
        assertEquals("s6", dataList.get(1));

        List dataList_3 = new ArrayList();
        dataList_3.add("s7");
        dataList_3.add("s8");
        adapter.insertItems(dataList_3, -1);
        assertEquals(8, adapter.getItemCount());
        assertEquals("s7", dataList.get(0));
        assertEquals("s8", dataList.get(1));

        List dataList_4 = new ArrayList();
        dataList_4.add("s9");
        dataList_4.add("s10");
        adapter.insertItems(dataList_4, 100);
        assertEquals(10, adapter.getItemCount());
        assertEquals("s9", dataList.get(8));
        assertEquals("s10", dataList.get(9));

        List nullList = null;
        adapter.insertItems(nullList, 0);
        assertEquals(10, adapter.getItemCount());
    }

    @Test @UiThreadTest
    public void adapter_removeItem() {
        BasicAdapterTestActivity activity = mActivityRule.getActivity();
        BasicAdapter adapter = activity.getAdapter();
        List dataList = adapter.getDataList();

        adapter.removeItem("null");

        String s1 = "s1", s2 = "s2", s3 = "s3";
        adapter.appendItem(s1);
        adapter.appendItem(s2);
        adapter.appendItem(s3);

        adapter.removeItem(null);
        assertEquals(3, adapter.getItemCount());

        adapter.removeItem(s1);
        assertEquals(s2, dataList.get(0));
        assertEquals(s3, dataList.get(1));

        adapter.removeItem(s2);
        assertEquals(s3, dataList.get(0));

        adapter.removeItem(s3);
        assertEquals(0, adapter.getItemCount());
    }

    @Test @UiThreadTest
    public void adapter_removeItem_Position() {
        BasicAdapterTestActivity activity = mActivityRule.getActivity();
        BasicAdapter adapter = activity.getAdapter();
        List dataList = adapter.getDataList();

        adapter.removeItem(0);

        String s1 = "s1", s2 = "s2", s3 = "s3";
        adapter.appendItem(s1);
        adapter.appendItem(s2);
        adapter.appendItem(s3);

        adapter.removeItem(1);
        assertEquals(2, adapter.getItemCount());
        assertEquals(s1, dataList.get(0));
        assertEquals(s3, dataList.get(1));

        adapter.removeItem(1);
        assertEquals(s1, dataList.get(0));

        adapter.removeItem(0);
        assertEquals(0, adapter.getItemCount());

        adapter.appendItem(s1);
        adapter.appendItem(s2);
        adapter.removeItem(-1);
        assertEquals(2, adapter.getItemCount());
        adapter.removeItem(100);
        assertEquals(2, adapter.getItemCount());
    }

    @Test @UiThreadTest
    public void adapter_removeItems() {
        BasicAdapterTestActivity activity = mActivityRule.getActivity();
        BasicAdapter adapter = activity.getAdapter();
        List dataList = adapter.getDataList();

        adapter.removeItems(0, 100);
        adapter.removeItems(-1, 100);

        List newDataList = new ArrayList();
        for(int i = 0; i < 100; i++) {
            newDataList.add("String" + i);
        }
        adapter.appendItems(newDataList);

        adapter.removeItems(-1, 1);
        adapter.removeItems(200, 1);
        assertEquals(0 , adapter.removeItems(1, 0));
        assertEquals(100, adapter.getItemCount());

        assertEquals(1, adapter.removeItems(99, 100));
        assertEquals(99, adapter.getItemCount());

        assertEquals(5, adapter.removeItems(90, 5));
        assertEquals(94, adapter.getItemCount());
        assertEquals("String95", dataList.get(90));
        assertEquals("String96", dataList.get(91));
    }
}