package com.wstro.examplecollect.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * ClassName: CommonAdapter
 * Function:
 * Date:     2017/8/24 14:21
 *
 * @author pengl
 * @see
 */
public abstract class CommonAdapter<T> extends BaseQuickAdapter<T,BaseViewHolder> {
    public CommonAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, T item) {
        convertViewItem(holder,item);
    }

    public abstract void convertViewItem(BaseViewHolder holder, T item);
}
