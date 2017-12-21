package com.bigw.tt.foundation.basicadapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigw.tt.foundation.R;
import com.bigw.tt.fundamentallib.adapter.BasicAdapter;

/**
 * Created by bigw on 15/12/2017.
 */

public class BasicAdapterTestActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, BasicAdapterTestActivity.class));
    }

    private BasicAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public BasicAdapter getAdapter() {
        return mAdapter;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_adpater);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BasicAdapter();
        mAdapter.register(String.class, new StringViewHolder.Factory(), null);
        mRecyclerView.setAdapter(mAdapter);

        findViewById(R.id.append).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.appendItem("string");
            }
        });
    }

    private StringViewHolder.ActionListener mActionListener = new StringViewHolder.ActionListener() {

        @Override
        public void onItemClicked(String s, int adapterPosition) {
        }
    };
}
