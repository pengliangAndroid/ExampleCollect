package com.wstro.examplecollect.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.wstro.app.common.base.BaseFragment;
import com.wstro.examplecollect.R;
import com.wstro.examplecollect.adapter.CommonAdapter;
import com.wstro.examplecollect.widget.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * ClassName: CalendarFragment
 * Function:
 * Date:     2017/9/19 0019 14:51
 *
 * @author Administrator
 * @see
 */
public class CalendarFragment extends BaseFragment {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private CommonAdapter<DateInfo> adapter;

    public static CalendarFragment newInstance() {
        Bundle args = new Bundle();

        CalendarFragment fragment = new CalendarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_calendar;
    }

    @Override
    protected void initViewsAndEvents(View view, Bundle bundle) {
        initRecyclerView();

        initAdapter();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),8));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter badapter, View view, int position) {
                if(!TextUtils.isEmpty(adapter.getItem(position).getStatusStr())) {
                    CalendarDialog.newInstance("")
                            .setDimAmount(0.3f)
                            .setShowBottom(true)
                            .show(getActivity().getSupportFragmentManager());
                }
            }
        });
    }

    private void initAdapter() {
        List<DateInfo> dataList = generateData();
        adapter = new CommonAdapter<DateInfo>(R.layout.list_header_item,dataList) {
            @Override
            public void convertViewItem(BaseViewHolder holder, DateInfo item) {
                int position = holder.getLayoutPosition();

                if(position == 0){
                    holder.itemView.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_top_left_radius_shape));
                }else if(position == 7){
                    holder.itemView.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_top_right_radius_shape));
                }

                if(item.isHeader()) {
                    if(TextUtils.isEmpty(item.getDateName()))
                        return;

                    holder.setText(R.id.tv_name_1,item.getDateName());
                    holder.setText(R.id.tv_name_2,item.getWeekName());

                    holder.setVisible(R.id.tv_name_3,false);
                }else{
                    if(!TextUtils.isEmpty(item.getDateName())){
                        holder.setTextColor(R.id.tv_name_3, Color.parseColor("#999999"));
                        holder.setText(R.id.tv_name_3,item.getDateName());
                    }else{
                        if(!TextUtils.isEmpty(item.getStatusStr())) {
                            holder.setText(R.id.tv_name_3, item.getStatusStr());
                            holder.setTextColor(R.id.tv_name_3, Color.WHITE);
                            holder.setBackgroundColor(R.id.tv_name_3, Color.parseColor("#00D4C4"));
                        }
                    }

                    holder.setVisible(R.id.tv_name_3,true);
                    holder.setVisible(R.id.ll_header,false);
                }
            }
        };

        recyclerView.setAdapter(adapter);
    }

    private List<DateInfo> generateData() {
        List<DateInfo> list = new ArrayList<>();

        list.add(new DateInfo("","",true,false,""));
        list.add(new DateInfo("周一","09-11",true,false,""));
        list.add(new DateInfo("周二","09-12",true,false,""));
        list.add(new DateInfo("周三","09-13",true,false,""));
        list.add(new DateInfo("周四","09-14",true,false,""));
        list.add(new DateInfo("周五","09-15",true,false,""));
        list.add(new DateInfo("周六","09-16",true,false,""));
        list.add(new DateInfo("周日","09-17",true,false,""));


        list.add(new DateInfo("上午","",false,false,""));
        list.add(new DateInfo("","",false,false,""));
        list.add(new DateInfo("","",false,false,"点击预约"));
        list.add(new DateInfo("","",false,false,""));
        list.add(new DateInfo("","",false,false,""));
        list.add(new DateInfo("","",false,false,""));
        list.add(new DateInfo("","",false,false,""));
        list.add(new DateInfo("","",false,false,""));

        list.add(new DateInfo("下午","",false,false,""));
        list.add(new DateInfo("","",false,false,""));
        list.add(new DateInfo("","",false,false,"点击预约"));
        list.add(new DateInfo("","",false,false,""));
        list.add(new DateInfo("","",false,false,""));
        list.add(new DateInfo("","",false,false,""));
        list.add(new DateInfo("","",false,false,""));
        list.add(new DateInfo("","",false,false,""));

        return list;
    }

    @Override
    protected void initData() {

    }

    static class DateInfo{
        private String dateName;

        private String weekName;

        private boolean isHeader;

        private boolean isFull;

        private String statusStr;

        public DateInfo(String dateName, String weekName,boolean isHeader, boolean isFull, String statusStr) {
            this.dateName = dateName;
            this.weekName = weekName;
            this.isHeader = isHeader;
            this.isFull = isFull;
            this.statusStr = statusStr;
        }

        public boolean isHeader() {
            return isHeader;
        }

        public void setHeader(boolean header) {
            isHeader = header;
        }

        public String getDateName() {
            return dateName;
        }

        public void setDateName(String dateName) {
            this.dateName = dateName;
        }

        public String getWeekName() {
            return weekName;
        }

        public void setWeekName(String weekName) {
            this.weekName = weekName;
        }

        public boolean isFull() {
            return isFull;
        }

        public void setFull(boolean full) {
            isFull = full;
        }

        public String getStatusStr() {
            return statusStr;
        }

        public void setStatusStr(String statusStr) {
            this.statusStr = statusStr;
        }
    }
}
