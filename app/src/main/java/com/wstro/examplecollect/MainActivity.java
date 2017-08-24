package com.wstro.examplecollect;

import android.os.Bundle;

import com.wstro.app.common.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents(Bundle bundle) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initToolbar(Bundle bundle) {

    }

    @OnClick(R.id.btn_common_list)
    public void onViewClicked() {
        CommonListActivity.start(this);
    }
}
