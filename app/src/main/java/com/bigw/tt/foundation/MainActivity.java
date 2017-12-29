package com.bigw.tt.foundation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bigw.tt.foundation.basefragment.BaseFragmentExampleActivity;
import com.bigw.tt.foundation.basefragment.BaseFragmentInViewPagerActivity;
import com.bigw.tt.foundation.basicadapter.BasicAdapterTestActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BasicAdapterTestActivity.start(this);
        BaseFragmentExampleActivity.start(this);
        //BaseFragmentInViewPagerActivity.start(this);
    }
}
