package com.wstro.examplecollect.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wstro.app.common.base.BaseActivity;
import com.wstro.examplecollect.R;
import com.wstro.examplecollect.widget.TranslucentScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ToolbarActivity extends BaseActivity {

    @Bind(R.id.scrollView)
    TranslucentScrollView scrollView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_left_img_text)
    TextView toolbarLeftImgText;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar_right_image)
    ImageView toolbarRightImage;

    @Bind(R.id.iv_image)
    ImageView ivImage;

    public static void start(Context context) {
        Intent starter = new Intent(context, ToolbarActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_toolbar;
    }

    @Override
    protected void initViewsAndEvents(Bundle bundle) {
        getSupportActionBar().hide();

        /*scrollView.setTranslucentListener(new TranslucentScrollView.TranslucentListener() {
            @Override
            public void onTranslucentChanged(float alpha) {
                LogUtil.d("alpha:" + alpha);
                alpha = 1 - alpha;
                toolbar.setAlpha(alpha);

                if (alpha > 0.7) {
                    toolbarLeftImgText.setVisibility(View.VISIBLE);
                    toolbarTitle.setVisibility(View.VISIBLE);
                    toolbarRightImage.setVisibility(View.VISIBLE);
                }else if(alpha < 0.3){
                    toolbarLeftImgText.setVisibility(View.GONE);
                    toolbarTitle.setVisibility(View.GONE);
                    toolbarRightImage.setVisibility(View.GONE);
                }

            }
        });*/

        scrollView.setPullZoomView(ivImage);
        //scrollView.setTransView(toolbar);
        scrollView.setTranslucentChangedListener(new TranslucentScrollView.TranslucentChangedListener() {
            @Override
            public void onTranslucentChanged(int transAlpha) {
                float alpha = transAlpha / 255.0f;
                toolbar.setAlpha(alpha);
                if (alpha > 0.7) {
                    toolbarLeftImgText.setVisibility(View.VISIBLE);
                    toolbarTitle.setVisibility(View.VISIBLE);
                    toolbarRightImage.setVisibility(View.VISIBLE);
                }else if(alpha < 0.3){
                    toolbarLeftImgText.setVisibility(View.GONE);
                    toolbarTitle.setVisibility(View.GONE);
                    toolbarRightImage.setVisibility(View.GONE);
                }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
