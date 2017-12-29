package com.bigw.tt.fundamentallib.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bigw.tt.fundamentallib.R;

/**
 * Created by bigw on 27/12/2017.
 */

public class TestBaseFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout_frame);
    }
}
