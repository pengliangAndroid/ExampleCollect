package com.wstro.examplecollect.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wstro.app.common.base.BaseActivity;
import com.wstro.examplecollect.R;
import com.wstro.examplecollect.widget.CircleProgressBar;
import com.wstro.examplecollect.widget.RippleBackground;

import butterknife.Bind;
import butterknife.OnClick;


public class VoiceActivity extends BaseActivity {


    @Bind(R.id.tv_tip)
    TextView tvTip;
    @Bind(R.id.centerImage)
    ImageView centerImage;
    @Bind(R.id.content)
    RippleBackground content;
    @Bind(R.id.circleProgressbar)
    CircleProgressBar circleProgressbar;

    public static void start(Context context) {
        Intent starter = new Intent(context, VoiceActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        setStatusCompat(false);
        return R.layout.activity_voice;
    }

    @Override
    protected void initViewsAndEvents(Bundle bundle) {

    }

    @Override
    protected void initData() {
        circleProgressbar.setProgress(75);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initToolbar(Bundle bundle) {

    }

    @OnClick(R.id.btn_start)
    public void onClick() {
        if (content.isRippleAnimationRunning()) {
            content.stopRippleAnimation();
        } else {
            content.startRippleAnimation();
        }
    }
}
