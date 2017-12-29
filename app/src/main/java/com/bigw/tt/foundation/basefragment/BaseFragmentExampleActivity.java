package com.bigw.tt.foundation.basefragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bigw.tt.foundation.R;
import com.bigw.tt.fundamentallib.adapter.ViewHolderInfoManager;
import com.bigw.tt.fundamentallib.placeholderview.ContentViewSwitcher;

/**
 * Created by bigw on 26/12/2017.
 */

public class BaseFragmentExampleActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, BaseFragmentExampleActivity.class));
    }

    private DisplayStringListFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment_example);
        mFragment = DisplayStringListFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, mFragment).commit();
    }

    public ContentViewSwitcher getContentViewSwitcher() {
        return mFragment.getContentViewSwitcher();
    }
}
