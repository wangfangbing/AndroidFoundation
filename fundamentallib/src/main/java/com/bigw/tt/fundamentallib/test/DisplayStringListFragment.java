package com.bigw.tt.foundation.basefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigw.tt.foundation.R;
import com.bigw.tt.foundation.common.StringViewHolder;
import com.bigw.tt.fundamentallib.adapter.BasicAdapter;
import com.bigw.tt.fundamentallib.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigw on 26/12/2017.
 */

public class DisplayStringListFragment extends BaseFragment {

    public static DisplayStringListFragment newInstance() {
        return new DisplayStringListFragment();
    }

    private RecyclerView mRecyclerView;
    private BasicAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_display_string_list, container, false);
        mRecyclerView = contentView.findViewById(R.id.recyclerView);
        Log.d("TestLog", "onCreateView " + contentView);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TestLog", "onViewCreated " + view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new BasicAdapter();
        mAdapter.register(String.class, new StringViewHolder.Factory(), null);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.appendItems(generateStringList());
    }

    private List<String> generateStringList() {
        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            stringList.add("string:" + i);
        }
        return stringList;
    }
}
