package com.wstro.examplecollect.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wstro.app.common.base.BaseActivity;
import com.wstro.app.common.utils.DeviceUtils;
import com.wstro.examplecollect.R;
import com.wstro.examplecollect.widget.WrapContentHeightViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class ScrollHoverActivity extends BaseActivity {
    @Bind(R.id.tv_1)
    TextView tv1;
    @Bind(R.id.tv_2)
    TextView tv2;
    @Bind(R.id.tab_line)
    View tabLine;
    @Bind(R.id.view_pager)
    WrapContentHeightViewPager viewPager;


    /*@Bind(R.id.ll_top_view)
    LinearLayout llTopView;
    @Bind(R.id.scrollView)
    HoverScrollView scrollView;
    @Bind(R.id.ll_flow_view)
    LinearLayout llFlowView;

    @Bind(R.id.tv_1)
    TextView textView;*/
    private int curIndex = 0;
    private int leftMargin1,leftMargin2;

    TextPagerAdapter pagerAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, ScrollHoverActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        //return R.layout.activity_scroll_pinned;
        return R.layout.activity_scroll_pinned_1;
    }

    @Override
    protected void initViewsAndEvents(Bundle bundle) {
        /*scrollView.setTopView(llTopView);
        scrollView.setFlowView(llFlowView);
        textView.setText("xxxx");*/

        initTabline();

        pagerAdapter = new TextPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTabSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTabline() {
        int width = DeviceUtils.deviceWidth(this);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tabLine.getLayoutParams();
        params.leftMargin = (width/2 - DeviceUtils.dp2px(this,40))/2;
        leftMargin1 = params.leftMargin;
        leftMargin2 = 3*width/4 - DeviceUtils.dp2px(this,40)/2;
        tabLine.setLayoutParams(params);
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

    @OnClick({R.id.tv_1, R.id.tv_2})
    public void onClick(View view) {
        resetStatus();

        switch (view.getId()) {
            case R.id.tv_1:
                //setTabSelection(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_2:
                //setTabSelection(1);
                viewPager.setCurrentItem(1);
                break;
        }
    }

    private void setTabSelection(int index){
        if(index == curIndex)
            return;
        curIndex = index;

        if(index == 0) {
            tv1.setTextColor(getResources().getColor(R.color.blue_light));
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tabLine.getLayoutParams();
            params.leftMargin = leftMargin1;
            tabLine.setLayoutParams(params);
        }else if(index == 1){
            tv2.setTextColor(getResources().getColor(R.color.blue_light));
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tabLine.getLayoutParams();
            params.leftMargin = leftMargin2;
            tabLine.setLayoutParams(params);
        }
    }

    private void resetStatus(){
        tv1.setTextColor(getResources().getColor(R.color.black33));
        tv2.setTextColor(getResources().getColor(R.color.black33));
    }


    public static class TextPagerAdapter extends FragmentPagerAdapter{

        List<TextFragment> fragmentList;

        public TextPagerAdapter(FragmentManager fm) {
            super(fm);

            fragmentList = new ArrayList<>();
            fragmentList.add(TextFragment.newInstance());
            fragmentList.add(TextFragment.newInstance());
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
