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

import com.bigw.tt.foundation.R;

/**
 * Created by bigw on 27/12/2017.
 */

public class BaseFragmentInViewPagerActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, BaseFragmentInViewPagerActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment_in_view_pager);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
    }


    private static class MyViewPagerAdapter extends FragmentPagerAdapter {
        private String[] titles = {"tab1", "tab2", "tab3"};
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DisplayStringListFragment.newInstance();
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
