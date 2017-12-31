package com.bigw.tt.fundamentallib.adapter;

import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;

import com.bigw.tt.fundamentallib.adapter.autopager.AutoPagerAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by bigw on 29/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class AutoPageAdapterTest {

    @Test @UiThreadTest
    public void checkSetItem() {
        AutoPagerAdapter adapter = new AutoPagerAdapter();
        adapter.appendItem("string0");
        adapter.setItem("string1");
        assertEquals(2, adapter.getItemCount());
        assertEquals("string1", adapter.getDataList().get(0));
    }

    @Test @UiThreadTest
    public void checkSetDataList() {
        AutoPagerAdapter adapter = new AutoPagerAdapter();
        adapter.appendItem("string0");
        ArrayList arrayList = new ArrayList();
        arrayList.add("string1");
        arrayList.add("string2");
        adapter.setDataList(arrayList);

        assertEquals(3, adapter.getItemCount());
        assertThat(arrayList, is(adapter.getDataList()));
        Class clz = adapter.getDataList().get(2).getClass();
        assertNotEquals(clz, String.class);
    }

    @Test @UiThreadTest
    public void checkInsertItem() {
        AutoPagerAdapter adapter = new AutoPagerAdapter();
        adapter.setItem("string0");
        assertEquals(2, adapter.getItemCount());
        adapter.insertItem("string1", 10);
        assertEquals(3, adapter.getItemCount());
        assertEquals("string0", adapter.getDataList().get(0));
        assertEquals("string1", adapter.getDataList().get(1));
        assertFalse(adapter.getDataList().get(2) instanceof String);
    }

    @Test @UiThreadTest
    public void checkInsertDataList() {
        AutoPagerAdapter adapter = new AutoPagerAdapter();
        adapter.setItem("string0");

        ArrayList dataList = new ArrayList();
        dataList.add("string1");
        dataList.add("string2");
        adapter.insertDataList(dataList, 3);

        assertEquals(4, adapter.getItemCount());
        assertEquals("string2", adapter.getDataList().get(2));
        assertFalse(adapter.getDataList().get(3) instanceof String);
    }

    @Test @UiThreadTest
    public void checkAppendInsertItem() {
        AutoPagerAdapter adapter = new AutoPagerAdapter();
        adapter.setItem("string0");
        adapter.appendItem("string1");
        assertEquals(3, adapter.getItemCount());
        assertEquals("string1", adapter.getDataList().get(1));
        assertFalse(adapter.getDataList().get(2)  instanceof String);
    }

    @Test @UiThreadTest
    public void checkAppendDataList() {
        AutoPagerAdapter adapter = new AutoPagerAdapter();
        ArrayList dataList = new ArrayList();
        dataList.add("s0");
        dataList.add("s1");
        adapter.setDataList(dataList);

        ArrayList newData = new ArrayList();
        newData.add("s2");
        newData.add("s3");
        adapter.appendDataList(newData);
        assertEquals("s3", adapter.getDataList().get(3));
    }
}
