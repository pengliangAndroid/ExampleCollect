package com.wstro.examplecollect;

import android.os.Bundle;
import android.view.View;

import com.wstro.app.common.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        setStatusCompat(false);
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

    @OnClick({R.id.btn_common_list,R.id.btn_sticky_list})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_common_list:
                CommonListActivity.start(this);
                break;
            case R.id.btn_sticky_list:
                RecyclerViewPageActivity.start(this);
                break;
        }

    }
}
