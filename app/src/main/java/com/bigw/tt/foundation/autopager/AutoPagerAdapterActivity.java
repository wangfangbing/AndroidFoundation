package com.bigw.tt.foundation.autopager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bigw.tt.foundation.R;
import com.bigw.tt.foundation.common.StringViewHolder;
import com.bigw.tt.fundamentallib.adapter.autopager.AutoPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bigw on 31/12/2017.
 */

public class AutoPagerAdapterActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, AutoPagerAdapterActivity.class));
    }

    private RecyclerView mRecyclerView;
    private AutoPagerAdapter mAdapter;
    private Random mRandom = new Random();
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_pager_adapter);
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new AutoPagerAdapter();
        mAdapter.register(String.class, new StringViewHolder.Factory(), null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        findViewById(R.id.success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAppendDataList(20);
            }
        });
        findViewById(R.id.empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAppendDataList(0);
            }
        });
        findViewById(R.id.error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setFooterStateAsError();
            }
        });
        mAdapter.setOnPageDownListener(new AutoPagerAdapter.OnPageDownListener() {
            @Override
            public void onPageDown(int page, int pageCount) {
                Toast.makeText(AutoPagerAdapterActivity.this, "page " + page, Toast.LENGTH_SHORT).show();
                Log.d("Test", "onPageDown " + page);
                if (mRandom.nextBoolean()) {
                    postAppendDataList(-1);
                } else {
                    postAppendDataList(20);
                }
            }
        });
    }

    private List generateStringList(int size) {
        ArrayList dataList = new ArrayList();
        for(int i = 0; i < size; i++) {
            dataList.add("string" + i);
        }
        return dataList;
    }

    private void postAppendDataList(final int size) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (size < 0) {
                    mAdapter.setFooterStateAsError();
                } else {
                    mAdapter.appendDataList(generateStringList(size));
                }
            }
        }, 2 * 1000);
    }
}
