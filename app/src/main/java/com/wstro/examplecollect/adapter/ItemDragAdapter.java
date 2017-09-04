package com.wstro.examplecollect.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wstro.examplecollect.R;

import java.util.List;

public class ItemDragAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {
    public ItemDragAdapter(List data) {
        super(R.layout.list_pk_info_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name, item);
    }
}