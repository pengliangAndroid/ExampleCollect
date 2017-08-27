package com.wstro.examplecollect;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wstro.app.common.base.BaseActivity;
import com.wstro.app.common.widget.DividerItemDecoration;
import com.wstro.examplecollect.model.PersonInfo;
import com.wstro.examplecollect.model.PinnedHeaderEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class StickyHeaderListActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    BaseHeaderAdapter<PinnedHeaderEntity<PersonInfo>> headerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sticky_header_list;
    }

    @Override
    protected void initViewsAndEvents(Bundle bundle) {
        initRecyclerView();
        initAdapter();
    }

    private void initAdapter() {
        List<PinnedHeaderEntity<PersonInfo>> list = new ArrayList<>();

        /*list.add(new PinnedHeaderEntity<PersonInfo>());

        headerAdapter = new BaseHeaderAdapter<PinnedHeaderEntity>() {
            @Override
            protected void addItemTypes() {

            }

            @Override
            protected void convert(BaseViewHolder helper, PinnedHeaderEntity item) {

            }
        };*/
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
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
