package com.wstro.examplecollect.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.wstro.app.common.base.BaseActivity;
import com.wstro.examplecollect.R;
import com.wstro.examplecollect.widget.HoverScrollView;

import butterknife.Bind;

public class ScrollHoverActivity extends BaseActivity {


    @Bind(R.id.ll_top_view)
    LinearLayout llTopView;
    @Bind(R.id.scrollView)
    HoverScrollView scrollView;
    @Bind(R.id.ll_flow_view)
    LinearLayout llFlowView;

    public static void start(Context context) {
        Intent starter = new Intent(context, ScrollHoverActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroll_pinned;
        //return R.layout.activity_scroll_pinned_1;
    }

    @Override
    protected void initViewsAndEvents(Bundle bundle) {
        scrollView.setTopView(llTopView);
        scrollView.setFlowView(llFlowView);

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
}
