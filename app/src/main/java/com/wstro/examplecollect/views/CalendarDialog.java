package com.wstro.examplecollect.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.othershe.nicedialog.BaseNiceDialog;
import com.othershe.nicedialog.ViewHolder;
import com.wstro.app.common.utils.LogUtil;
import com.wstro.examplecollect.R;
import com.wstro.examplecollect.adapter.CommonAdapter;
import com.wstro.examplecollect.widget.GridSpacingDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: CalendarDailog
 * Function:
 * Date:     2017/9/20 0020 14:01
 *
 * @author Administrator
 * @see
 */
public class CalendarDialog extends BaseNiceDialog {
    private String type;

    RecyclerView recyclerView;

    CommonAdapter<String> adapter;

    public static CalendarDialog newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        CalendarDialog dialog = new CalendarDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        type = bundle.getString("type");
    }

    @Override
    public int intLayoutId() {
        return R.layout.dailog_calendar;
    }

    @Override
    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
        recyclerView = holder.getView(R.id.recycler_view);
        List<String> list = new ArrayList<>();
        list.add("13:00-14:00");
        list.add("14:00-15:00");
        list.add("15:00-16:00");
        list.add("16:00-17:00");
        adapter = new CommonAdapter<String>(R.layout.list_calendar_time_item,list) {
            @Override
            public void convertViewItem(BaseViewHolder holder, String item) {
                holder.setText(R.id.tv_time,item);
            }
        };
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.addItemDecoration(new GridSpacingDecoration(40));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtil.d("onCLick");
            }
        });
    }
}
