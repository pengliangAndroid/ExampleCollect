package com.wstro.examplecollect.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.wstro.app.common.base.BaseActivity;
import com.wstro.app.common.utils.LogUtil;
import com.wstro.examplecollect.R;
import com.wstro.examplecollect.widget.BottomTabBar;

public class BottomTabActivity extends BaseActivity {

    private Fragment fragment1,fragment2,fragment3;

    private BottomTabBar bottomTabBar;

    public static void start(Context context) {
        Intent starter = new Intent(context, BottomTabActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        setStatusCompat(false);
        return R.layout.activity_bottom_tab;
    }

    @Override
    protected void initViewsAndEvents(Bundle bundle) {
        bottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);

        bottomTabBar
                .addTabItem("首页",R.mipmap.ic_home_selected,R.mipmap.ic_home)
                .addTabItem("消息",R.mipmap.ic_msg_selected,R.mipmap.ic_msg)
                .addTabItem("我的",R.mipmap.ic_my_selected,R.mipmap.ic_my)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChanged(int position) {
                        LogUtil.d("onTabChanged:"+position);

                        setTabSelection(position);
                    }
                })
                .create();
        bottomTabBar.setCurrentTab(0);
    }


    public void setTabSelection(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);

        switch (index) {
            case 0:
                if (fragment1 == null) {
                    fragment1 = TestFragment.newInstance("TestFragment 1");
                    transaction.add(R.id.fl_content, fragment1, fragment1.getClass().getSimpleName());
                } else {
                    transaction.show(fragment1);
                }

                break;
            case 1:

                if (fragment2 == null) {
                    fragment2 = TestFragment.newInstance("TestFragment 2");
                    transaction.add(R.id.fl_content, fragment2, fragment2.getClass().getSimpleName());
                } else {
                    transaction.show(fragment2);
                }

                break;
            case 2:

                if (fragment3 == null) {
                    fragment3 = TestFragment.newInstance("TestFragment 3");
                    transaction.add(R.id.fl_content, fragment3, fragment3.getClass().getSimpleName());
                } else {
                    transaction.show(fragment3);
                }

                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (fragment1 != null) {
            transaction.hide(fragment1);
        }
        if (fragment2 != null) {
            transaction.hide(fragment2);
        }
        if (fragment3 != null) {
            transaction.hide(fragment3);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initToolbar(Bundle bundle) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
