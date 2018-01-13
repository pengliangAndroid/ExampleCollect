package com.wstro.examplecollect.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.wstro.app.common.base.BaseActivity;
import com.wstro.examplecollect.R;

import butterknife.Bind;
import butterknife.OnClick;

public class RippleActivity extends BaseActivity {


    @Bind(R.id.tv_msg)
    TextView tvMsg;

    public static void start(Context context) {
        Intent starter = new Intent(context, RippleActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ripple;
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


    @OnClick(R.id.tv_msg)
    public void onViewClicked() {
    }
}
