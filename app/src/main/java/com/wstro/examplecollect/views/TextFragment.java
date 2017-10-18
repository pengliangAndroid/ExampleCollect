package com.wstro.examplecollect.views;

import android.os.Bundle;
import android.view.View;

import com.wstro.app.common.base.BaseFragment;
import com.wstro.examplecollect.R;

/**
 * ClassName: TextFragment
 * Function:
 * Date:     2017/10/18 0018 12:01
 *
 * @author pengl
 * @see
 */
public class TextFragment extends BaseFragment {

    public static TextFragment newInstance() {
        Bundle args = new Bundle();

        TextFragment fragment = new TextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_text;
    }

    @Override
    protected void initViewsAndEvents(View view, Bundle bundle) {

    }

    @Override
    protected void initData() {

    }
}
