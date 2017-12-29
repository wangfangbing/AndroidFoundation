package com.bigw.tt.foundation.basefragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bigw.tt.foundation.R;
import com.bigw.tt.fundamentallib.fragment.BaseFragment;

/**
 * Created by bigw on 29/12/2017.
 */

public class SwitchContentViewActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, SwitchContentViewActivity.class));
    }

    private BaseFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        mFragment = DisplayStringListFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, mFragment).commit();

        findViewById(R.id.contentView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.switchToContentView();
            }
        });
        findViewById(R.id.empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.switchToEmptyView(R.drawable.ic_launcher_background, R.string.empty_data_tips);
            }
        });
        findViewById(R.id.error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragment.switchToErrorView(R.drawable.ic_launcher_background, R.string.error_data_tips);
            }
        });
    }
}
