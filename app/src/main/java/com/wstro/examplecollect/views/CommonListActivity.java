package com.wstro.examplecollect.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.classic.common.MultipleStatusView;
import com.wstro.app.common.base.BaseActivity;
import com.wstro.app.common.widget.DividerItemDecoration;
import com.wstro.examplecollect.Constants;
import com.wstro.examplecollect.R;
import com.wstro.examplecollect.adapter.CommonAdapter;
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

    @Bind(R.id.multiple_status_view)
    MultipleStatusView multipleStatusView;

    CommonAdapter<String> adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, CommonListActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        setStatusCompat(false);
        return R.layout.activity_common_list;
    }

    @Override
    protected void initViewsAndEvents(Bundle bundle) {
        initRecyclerView();
        initAdapter();

        swipeRefreshLayout.setOnRefreshListener(this);
        multipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.error_retry_view){
                    //showToast("重新加载");
                    multipleStatusView.showLoading();
                    loadNextPageData();
                }else if(v.getId() == R.id.no_network_retry_view){
                   /* showToast("检查网络");*/
                    try {
                        Intent intent =  new Intent(Settings.ACTION_WIFI_SETTINGS);
                        startActivity(intent);
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    private void initAdapter() {
        List<String> list = new ArrayList<>();
        adapter = new CommonAdapter<String>(R.layout.list_data_item, list) {
            @Override
            public void convertViewItem(BaseViewHolder holder, String item) {
                holder.setText(R.id.tv_name, item);
            }
        };

        adapter.openLoadAnimation();
        adapter.isFirstOnly(true);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!presenter.isHasMoreData()) {
                            boolean isHideEnd = adapter.getData().size() <= Constants.PAGE_SIZE;
                            adapter.loadMoreEnd(isHideEnd);
                        } else {
                            loadNextPageData();
                        }
                    }
                });
            }
        });
        adapter.setAutoLoadMoreSize(Constants.PAGE_SIZE);
        recyclerView.setAdapter(adapter);
    }

    private void loadNextPageData() {
        presenter.getData();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.divider_item_shape));

    }

    @Override
    protected void initData() {
        presenter = new CommonListPresenter();
        presenter.attachView(this);

        multipleStatusView.showLoading();

        presenter.getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.load_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.show_empty){
            multipleStatusView.showEmpty();
        }else if(item.getItemId() == R.id.show_loading){
            multipleStatusView.showLoading();
        }else if(item.getItemId() == R.id.show_error){
            multipleStatusView.showError();
        }else if(item.getItemId() == R.id.show_not_network){
            multipleStatusView.showNoNetwork();
        }else if(item.getItemId() == R.id.show_content){
            multipleStatusView.showContent();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initToolbar(Bundle bundle) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.detachView();
    }


    @Override
    public void onRefresh() {
        presenter.setCurPage(1);
        presenter.getData();
    }

    @Override
    public void onGetDataSuccess(List<String> list) {
        multipleStatusView.showContent();

        if (swipeRefreshLayout.isRefreshing()) {
            adapter.setNewData(list);
            swipeRefreshLayout.setRefreshing(false);
            return;
        }

        if(list == null || list.size() == 0){
            if (adapter.getData().size() == 0) {
                multipleStatusView.showEmpty();
            } else {
                boolean isHideEnd = adapter.getData().size() <= Constants.PAGE_SIZE;
                adapter.loadMoreEnd(isHideEnd);
            }
        }else{
            adapter.addData(list);
            adapter.loadMoreComplete();
        }

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetDataFail(String error) {
        if(adapter.getData().size() == 0){
            multipleStatusView.showError();
        }else{
            adapter.loadMoreFail();
        }
    }
}
