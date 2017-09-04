package com.wstro.examplecollect.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration;
import com.oushangfeng.pinnedsectionitemdecoration.callback.OnHeaderClickListener;
import com.wstro.app.common.base.BaseActivity;
import com.wstro.examplecollect.R;
import com.wstro.examplecollect.adapter.BaseHeaderAdapter;
import com.wstro.examplecollect.model.PersonInfo;
import com.wstro.examplecollect.model.PinnedHeaderEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class StickyHeaderListActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    BaseHeaderAdapter<PinnedHeaderEntity<PersonInfo>> headerAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, StickyHeaderListActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        setStatusCompat(false);
        return R.layout.activity_sticky_header_list;
    }

    @Override
    protected void initViewsAndEvents(Bundle bundle) {
        initRecyclerView();
        initAdapter();
    }

    private void initAdapter() {
        List<PinnedHeaderEntity<PersonInfo>> list = new ArrayList<>();

        generateData(list);

        headerAdapter = new BaseHeaderAdapter<PinnedHeaderEntity<PersonInfo>>(list) {
            @Override
            protected void addItemTypes() {
                addItemType(TYPE_HEADER,R.layout.list_data_header);
                addItemType(TYPE_DATA,R.layout.list_content_data_item);
            }

            @Override
            protected void convert(BaseViewHolder helper, PinnedHeaderEntity<PersonInfo> item) {
                switch (item.getItemType()){
                    case TYPE_HEADER:
                        helper.setText(R.id.tv_name,item.getPinnedHeaderName());
                        break;
                    case TYPE_DATA:
                        helper.setText(R.id.tv_name,item.getData().getName());
                        break;
                }
            }
        };
        recyclerView.setAdapter(headerAdapter);
    }

    private void generateData(List<PinnedHeaderEntity<PersonInfo>> list) {
        PersonInfo infoA = new PersonInfo("A");
        list.add(new PinnedHeaderEntity<PersonInfo>(infoA, BaseHeaderAdapter.TYPE_HEADER,infoA.getName()));
        for (int i = 1; i < 6; i++) {
            PersonInfo obj = new PersonInfo("Item A"+i);
            list.add(new PinnedHeaderEntity<PersonInfo>(obj,BaseHeaderAdapter.TYPE_DATA,infoA.getName()));
        }

        PersonInfo infoB = new PersonInfo("B");
        list.add(new PinnedHeaderEntity<PersonInfo>(infoB,BaseHeaderAdapter.TYPE_HEADER,infoB.getName()));
        for (int i = 1; i < 6; i++) {
            PersonInfo obj = new PersonInfo("Item B"+i);
            list.add(new PinnedHeaderEntity<PersonInfo>(obj,BaseHeaderAdapter.TYPE_DATA,infoB.getName()));
        }

        PersonInfo infoC = new PersonInfo("C");
        list.add(new PinnedHeaderEntity<PersonInfo>(infoC,BaseHeaderAdapter.TYPE_HEADER,infoC.getName()));
        for (int i = 1; i < 6; i++) {
            PersonInfo obj = new PersonInfo("Item C"+i);
            list.add(new PinnedHeaderEntity<PersonInfo>(obj,BaseHeaderAdapter.TYPE_DATA,infoC.getName()));
        }

        PersonInfo infoD = new PersonInfo("D");
        list.add(new PinnedHeaderEntity<PersonInfo>(infoD,BaseHeaderAdapter.TYPE_HEADER,infoD.getName()));
        for (int i = 1; i < 6; i++) {
            PersonInfo obj = new PersonInfo("Item D"+i);
            list.add(new PinnedHeaderEntity<PersonInfo>(obj,BaseHeaderAdapter.TYPE_DATA,infoD.getName()));
        }
    }

    OnHeaderClickListener headerClickListener = new OnHeaderClickListener() {
        @Override
        public void onHeaderClick(View view, int id, int position) {
            Toast.makeText(context, "click, tag: " + headerAdapter.getData().get(position).getPinnedHeaderName(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onHeaderLongClick(View view, int id, int position) {
            Toast.makeText(context, "long click, tag: " + headerAdapter.getData().get(position).getPinnedHeaderName(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onHeaderDoubleClick(View view, int id, int position) {
            Toast.makeText(context, "double click, tag: " + headerAdapter.getData().get(position).getPinnedHeaderName(), Toast.LENGTH_SHORT).show();
        }
    };

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST,R.drawable.divider_item_shape));
        recyclerView.addItemDecoration(new PinnedHeaderItemDecoration.Builder(BaseHeaderAdapter.TYPE_HEADER).
                setDividerId(R.drawable.divider_item_shape).enableDivider(true)
                .setHeaderClickListener(headerClickListener).create());

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (headerAdapter.getItemViewType(i)) {
                    case BaseHeaderAdapter.TYPE_DATA:
                        PinnedHeaderEntity<PersonInfo> entity = headerAdapter.getData().get(i);
                        Toast.makeText(context, entity.getPinnedHeaderName() + ", position " + i + ", id " + entity.getData().getName(), Toast.LENGTH_SHORT).show();
                        break;
               /*     case BaseHeaderAdapter.TYPE_HEADER:
                        entity = headerAdapter.getData().get(i);
                        Toast.makeText(context, "click, tag: " + entity.getPinnedHeaderName(), Toast.LENGTH_SHORT).show();
                        break;*/
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
}
