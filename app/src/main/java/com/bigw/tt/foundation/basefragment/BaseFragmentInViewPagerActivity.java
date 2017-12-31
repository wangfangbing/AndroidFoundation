package com.bigw.tt.foundation.basefragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bigw.tt.foundation.R;
import com.bigw.tt.fundamentallib.fragment.BaseFragment;

/**
 * Created by bigw on 27/12/2017.
 */

public class BaseFragmentInViewPagerActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, BaseFragmentInViewPagerActivity.class));
    }

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment_in_view_pager);
        mViewPager = findViewById(R.id.viewPager);
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        findViewById(R.id.contentView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            findCurrentFragment().switchToContentView();
            }
        });
        findViewById(R.id.empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findCurrentFragment().switchToEmptyView(R.drawable.ic_launcher_background, R.string.empty_data_tips);
            }
        });
        findViewById(R.id.error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findCurrentFragment().switchToErrorView(R.drawable.ic_launcher_background, R.string.error_data_tips);
            }
        });

    }

    private BaseFragment findCurrentFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + mViewPager.getCurrentItem());
        return (BaseFragment) fragment;
    }

    private static class MyViewPagerAdapter extends FragmentPagerAdapter {
        private String[] titles = {"tab1", "tab2", "tab3"};
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public BaseFragment getItem(int position) {
            return DisplayStringListFragment1.newInstance();
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
