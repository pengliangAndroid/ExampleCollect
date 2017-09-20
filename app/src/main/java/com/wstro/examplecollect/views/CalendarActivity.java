package com.wstro.examplecollect.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.wstro.app.common.base.BaseActivity;
import com.wstro.examplecollect.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.relex.circleindicator.CircleIndicator;


public class CalendarActivity extends BaseActivity {


    @Bind(R.id.vp_default)
    ViewPager vpDefault;

    @Bind(R.id.ci_default)
    CircleIndicator ciDefault;

    private CalendarPageAdapter  adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, CalendarActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        setStatusCompat(false);
        return R.layout.activity_calendar;
    }

    @Override
    protected void initViewsAndEvents(Bundle bundle) {
        adapter = new CalendarPageAdapter(getSupportFragmentManager());

        vpDefault.setAdapter(adapter);
        ciDefault.setViewPager(vpDefault);
        vpDefault.setCurrentItem(0);
        vpDefault.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*if (position == 2) {
                    ciDefault.setVisibility(View.GONE);
                } else {
                    ciDefault.setVisibility(View.VISIBLE);
                }*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initToolbar(Bundle bundle) {

    }

    class CalendarPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public CalendarPageAdapter(FragmentManager fm) {
            super(fm);

            fragmentList = new ArrayList<>();

            for (int i = 0; i < 2; i++) {
                fragmentList.add(CalendarFragment.newInstance());
            }
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }


        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

    }


}
