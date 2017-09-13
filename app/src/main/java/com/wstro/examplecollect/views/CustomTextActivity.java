package com.wstro.examplecollect.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.TextView;

import com.wstro.app.common.base.BaseActivity;
import com.wstro.examplecollect.R;

import butterknife.Bind;

public class CustomTextActivity extends BaseActivity {

    @Bind(R.id.tv_1)
    TextView tv1;
    @Bind(R.id.tv_2)
    TextView tv2;
    @Bind(R.id.edt_1)
    EditText edt1;
    @Bind(R.id.edt_3)
    EditText edt3;
    @Bind(R.id.edt_4)
    EditText edt4;
    @Bind(R.id.edt_2)
    EditText edt2;

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomTextActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_text;
    }

    @Override
    protected void initViewsAndEvents(Bundle bundle) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/llhj.ttf");

        tv1.setTypeface(typeface);
        edt1.setTypeface(typeface);
        edt4.setTypeface(typeface);

        edt3.setTypeface(typeface);
        edt3.setTransformationMethod(new PasswordTransformationMethod());
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
