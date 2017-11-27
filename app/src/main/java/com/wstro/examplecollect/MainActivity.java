package com.wstro.examplecollect;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.wstro.app.common.base.BaseActivity;
import com.wstro.app.common.utils.LogUtil;
import com.wstro.app.common.widget.DividerItemDecoration;
import com.wstro.examplecollect.adapter.CommonAdapter;
import com.wstro.examplecollect.views.BottomTabActivity;
import com.wstro.examplecollect.views.CalendarActivity;
import com.wstro.examplecollect.views.CommonListActivity;
import com.wstro.examplecollect.views.CustomTextActivity;
import com.wstro.examplecollect.views.PullRefreshListActivity;
import com.wstro.examplecollect.views.RecyclerViewPageActivity;
import com.wstro.examplecollect.views.ScrollHoverActivity;
import com.wstro.examplecollect.views.StickyHeaderListActivity;
import com.wstro.examplecollect.views.ToolbarActivity;
import com.wstro.examplecollect.views.VoiceActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    CommonAdapter<String> adapter;

    private final String[] names = new String[]{
            "通用刷新加载列表","Pull刷新加载列表","列表顶部悬停列表","滑动页式列表(仿汽车之家)","自定义字体设置",
            "Toolbar透明度变化","语音播放动画","日历表","滚动悬停","底部Tab"
    };

    @Override
    protected int getLayoutId() {
        setStatusCompat(false);
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents(Bundle bundle) {
        LogUtil.d("xx");
        initRecyclerView();

        initAdapter();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST,R.drawable.divider_item_shape));
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter badapter, View view, int position) {
                switch (position){
                    case 0:
                        CommonListActivity.start(context);
                        break;
                    case 1:
                        PullRefreshListActivity.start(context);
                        break;
                    case 2:
                        StickyHeaderListActivity.start(context);
                        break;
                    case 3:
                        RecyclerViewPageActivity.start(context);
                        break;
                    case 4:
                        CustomTextActivity.start(context);
                        break;
                    case 5:
                        ToolbarActivity.start(context);
                        break;
                    case 6:
                        VoiceActivity.start(context);
                        break;
                    case 7:
                        CalendarActivity.start(context);
                        break;
                    case 8:
                        ScrollHoverActivity.start(context);
                        break;
                    case 9:
                        BottomTabActivity.start(context);
                        break;
                }
            }
        });
    }

    private void initAdapter() {
        List<String> list = Arrays.asList(names);
        adapter = new CommonAdapter<String>(R.layout.list_data_item,list) {
            @Override
            public void convertViewItem(BaseViewHolder holder, String item) {
                holder.setText(R.id.tv_name,item);
            }
        };
        adapter.openLoadAnimation();
        adapter.isFirstOnly(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initToolbar(Bundle bundle) {

    }


}
