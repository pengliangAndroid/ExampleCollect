package com.wstro.examplecollect.views;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wstro.app.common.base.BaseFragment;
import com.wstro.examplecollect.R;

import butterknife.Bind;

/**
 * ClassName: TestFramgemt
 * Function:
 * Date:     2017/11/27 22:41
 *
 * @author pengl
 * @see
 */
public class TestFragment extends BaseFragment {

    @Bind(R.id.tv_msg)
    TextView tvMsg;

    public static TestFragment newInstance(String name) {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        args.putString("name",name);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initViewsAndEvents(View view, Bundle bundle) {
        String name = getArguments().getString("name");
        tvMsg.setText(name);
    }

    @Override
    protected void initData() {

    }

}
