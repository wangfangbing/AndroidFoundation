package com.bigw.tt.foundation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bigw.tt.foundation.autopager.AutoPagerAdapterActivity;
import com.bigw.tt.foundation.basefragment.BaseFragmentInViewPagerActivity;
import com.bigw.tt.foundation.basefragment.SwitchContentViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BasicAdapterTestActivity.start(this);
        //BaseFragmentInViewPagerActivity.start(this);
        //SwitchContentViewActivity.start(this);
        AutoPagerAdapterActivity.start(this);
    }
}
