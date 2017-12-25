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
        mAdapter.register(Double.class, new DoubleViewHolder.DoubleViewHolderFactory(),  null);
        mRecyclerView.setAdapter(mAdapter);

        findViewById(R.id.append).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.appendItem(StringItemGenerator.next());
            }
        });
        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.insertItem(DoubleItemGenerator.next(), 0);
            }
        });
        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeItem(mAdapter.getItemCount() - 1);
            }
        });
    }

    private StringViewHolder.ActionListener mActionListener = new StringViewHolder.ActionListener() {

        @Override
        public void onItemClicked(String s, int adapterPosition) {
            //do nothing
        }
    };

    private static class StringItemGenerator {
        private static int COUNT = 0;
        public static String next() {
            return "string" + COUNT++;
        }
    }

    private static class DoubleItemGenerator {
        public static Double next() {
            return Math.random() * 10;
        }
    }
}
