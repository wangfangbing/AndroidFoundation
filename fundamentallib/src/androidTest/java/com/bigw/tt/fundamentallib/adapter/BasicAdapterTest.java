package com.bigw.tt.fundamentallib.adapter;

import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by bigw on 29/12/2017.
 */
@RunWith(AndroidJUnit4.class)
public class BasicAdapterTest {

    @Test @UiThreadTest
    public void checkSetItem() {
        BasicAdapter adapter = new BasicAdapter();
        adapter.appendItem("string0");
        adapter.setItem("string1");
        assertEquals("string1", adapter.getDataList().get(0));
    }

    @Test @UiThreadTest
    public void checkSetDataList() {
        BasicAdapter adapter = new BasicAdapter();
        adapter.appendItem("string0");

        ArrayList arrayList = new ArrayList();
        arrayList.add("string1");
        arrayList.add("string2");
        adapter.setDataList(arrayList);

        assertThat(arrayList, is(adapter.getDataList()));
    }
}
