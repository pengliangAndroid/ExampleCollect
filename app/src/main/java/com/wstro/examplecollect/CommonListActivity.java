package com.wstro.examplecollect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.classic.common.MultipleStatusView;
import com.wstro.app.common.base.BaseActivity;
import com.wstro.app.common.utils.NetUtils;
import com.wstro.app.common.widget.DividerItemDecoration;
import com.wstro.examplecollect.presenter.CommonListPresenter;
import com.wstro.examplecollect.view.CommonListView;
import com.wstro.examplecollect.widget.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class CommonListActivity extends BaseActivity implements CommonListView,SwipeRefreshLayout.OnRefreshListener {

    CommonListPresenter presenter;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    int index;

    BaseQuickAdapter<String, BaseViewHolder> adapter;

    @Bind(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    public static void start(Context context) {
        Intent starter = new Intent(context, CommonListActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_list;
    }

    @Override
    protected void initViewsAndEvents(Bundle bundle) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.divider_item_shape));


        List<String> list = new ArrayList<>();
        adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.list_data_item, list) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_name, item);
            }
        };

        adapter.openLoadAnimation();
        adapter.isFirstOnly(true);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(index % 3 == 1){
                            adapter.loadMoreFail();
                        }else if(index == 6) {
                            adapter.loadMoreEnd(false);
                        }else {
                            List<String> strings = generateData();
                            adapter.addData(strings);
                            adapter.loadMoreComplete();
                        }

                        index++;
                    }
                },1000);
            }
        });
        adapter.setAutoLoadMoreSize(10);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
        presenter = new CommonListPresenter();
        presenter.attachView(this);

        if(NetUtils.isConnected(this)) {
            multipleStatusView.showLoading();

            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    multipleStatusView.showContent();
                    List<String> strings = generateData();
                    adapter.addData(strings);
                }
            }, 1000);
        }else{
            multipleStatusView.showNoNetwork();
        }
    }

    @Override
    protected void initToolbar(Bundle bundle) {

    }


    private int size = 0;

    private List<String> generateData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("New Item" + i);
        }

        return list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.detachView();
    }


    @Override
    public void onRefresh() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                index = 0;
                List<String> strings = generateData();
                adapter.setNewData(strings);
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);

    }
}
