package com.bigw.tt.foundation.basefragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.bigw.tt.foundation.R;
import com.bigw.tt.foundation.common.StringViewHolder;
import com.bigw.tt.fundamentallib.adapter.BasicAdapter;
import com.bigw.tt.fundamentallib.fragment.BaseFragment;
import com.bigw.tt.fundamentallib.placeholderview.ContentViewSwitcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigw on 29/12/2017.
 */

public class DisplayStringListFragment1 extends BaseFragment {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private RecyclerView mRecyclerView;
    private BasicAdapter mAdapter;

    public static DisplayStringListFragment1 newInstance() {
        return new DisplayStringListFragment1();
    }

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_display_string_list_1, null);
        mRecyclerView = fragmentView.findViewById(R.id.recyclerView);

        SwipeRefreshLayout refreshLayout = fragmentView.findViewById(R.id.swipeRefreshLayout);
        setSwipeRefreshLayout(refreshLayout);

        ContentViewSwitcher switcher = fragmentView.findViewById(R.id.contentSwitcher);
        setContentViewSwitcher(switcher);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TestLog", "onViewCreated " + view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new BasicAdapter();
        mAdapter.register(String.class, new StringViewHolder.Factory(), null);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.appendDataList(generateStringList());
    }

    protected List<String> generateStringList() {
        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            stringList.add("string:" + i);
        }
        return stringList;
    }

    @Override
    protected void onRefreshFragment() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideSwipeRefreshLayout();
            }
        }, 1000);
    }
}
