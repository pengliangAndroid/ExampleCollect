package com.wstro.examplecollect.views;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.wstro.app.common.base.BaseActivity;
import com.wstro.app.common.utils.DeviceUtils;
import com.wstro.app.common.utils.LogUtil;
import com.wstro.examplecollect.R;
import com.wstro.examplecollect.adapter.CommonAdapter;
import com.wstro.examplecollect.adapter.ItemDragAdapter;
import com.wstro.examplecollect.model.PKDataInfo;
import com.wstro.examplecollect.utils.NumAnim;
import com.wstro.examplecollect.widget.ScoreProgressView;
import com.wstro.examplecollect.widget.TwoCircleIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class RecyclerViewPageActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerViewPager recyclerViewPager;

    @Bind(R.id.recycler_view_0)
    RecyclerView recyclerView;

    @Bind(R.id.recycler_view_1)
    RecyclerView recyclerView1;

    @Bind(R.id.ci_default)
    TwoCircleIndicator ciDefault;

    @Bind(R.id.tv_num_1)
    TextView tvNum1;

    @Bind(R.id.tv_num_2)
    TextView tvNum2;

    CommonAdapter<PKDataInfo> pkAdapter;
    CommonAdapter<PKDataInfo> pkAdapter1;

    private int mAnimatorResId = R.animator.scale_with_alpha;
    private int mAnimatorReverseResId = 0;

    private Animator mAnimatorOut;
    private Animator mAnimatorIn;

    @Bind(R.id.tv_pk)
    TextView tvPk;


    ItemDragAdapter adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, RecyclerViewPageActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        setStatusCompat(false);
        return R.layout.activity_recycler_view_page;
}

    @Override
    protected void initViewsAndEvents(Bundle bundle) {
        int width = DeviceUtils.deviceWidth(this);
        LogUtil.d(width+"....");
        mAnimatorIn = createAnimatorIn(this);
        mAnimatorOut = createAnimatorOut(this);

        //ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) recyclerViewPager.getLayoutParams();

        recyclerViewPager.setPadding(0,0,width/2 ,0);

        ArrayList<String> entities = new ArrayList<>();
        adapter = new ItemDragAdapter(entities);

        //LinearLayoutManager layout = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        GridLayoutManager layout = new GridLayoutManager(this,1, GridLayoutManager.HORIZONTAL,false);
        recyclerViewPager.setLayoutManager(layout);

        recyclerViewPager.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int fromPos, int pos) {
                LogUtil.d("fromPos:"+fromPos+",pos:"+pos);
                if(pos > (adapter.getData().size() - 2)){
                    recyclerViewPager.scrollToPosition(adapter.getData().size() - 2);
                }
            }
        });

        recyclerViewPager.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                if(newState ==  RecyclerView.SCROLL_STATE_IDLE){
                    startPkAnimation();

                }else if(newState == RecyclerView.SCROLL_STATE_DRAGGING ){
                    stopPkAnimation();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewPager);

        //开启拖拽
        adapter.enableDragItem(itemTouchHelper, R.id.fl_item, true);
        adapter.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                stopPkAnimation();
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                LogUtil.d("pos:"+pos);
                recyclerViewPager.scrollToPosition(pos);
                startPkAnimation();
            }
        });

        recyclerViewPager.setAdapter(adapter);

        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if(i % 2 == 0)
                dataList.add("Item " +i);
            else
                dataList.add("新余讯飞信息科技有限公司"+i);
        }

        adapter.setNewData(dataList);

        ciDefault.setViewPager(recyclerViewPager);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<PKDataInfo> list = createPKList();
        pkAdapter = new CommonAdapter<PKDataInfo>(R.layout.list_pk_data_item,list) {
            @Override
            public void convertViewItem(BaseViewHolder holder, PKDataInfo item) {
                ScoreProgressView scoreProgressView = holder.getView(R.id.score_progress_view);
                scoreProgressView.setValue(item.getValue1(),item.getValue2());

                holder.setText(R.id.tv_1,(int)item.getValue1()+"");
                holder.setText(R.id.tv_2,(int)item.getValue2()+"");
                holder.setText(R.id.tv_name,item.getName());
            }
        };

        recyclerView.setAdapter(pkAdapter);

        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        list = createPKList2();
        pkAdapter1 = new CommonAdapter<PKDataInfo>(R.layout.list_pk_data_item,list) {
            @Override
            public void convertViewItem(BaseViewHolder holder, PKDataInfo item) {
                ScoreProgressView scoreProgressView = holder.getView(R.id.score_progress_view);
                scoreProgressView.setValue(item.getValue1(),item.getValue2());

                holder.setText(R.id.tv_1,(int)item.getValue1()+"");
                holder.setText(R.id.tv_2,(int)item.getValue2()+"");
                holder.setText(R.id.tv_name,item.getName());
            }
        };

        recyclerView1.setAdapter(pkAdapter1);


    }


    @Override
    protected void initData() {
        startNumAnim();
    }

    private List<PKDataInfo> createPKList(){
        List<PKDataInfo> list = new ArrayList<>();
        float random = (float) Math.random();

        PKDataInfo pk1 = new PKDataInfo("主营业务收入（万元）",239*random,482*random);
        PKDataInfo pk2 = new PKDataInfo("主要产品产量（万吨）",182*random,130*random);
        PKDataInfo pk3 = new PKDataInfo("利润（万元）",300*random,210*random);
        PKDataInfo pk4 = new PKDataInfo("税收（万元）",30*random,20*random);
        PKDataInfo pk5 = new PKDataInfo("产品销量（%）",78*random,90*random);

        list.add(pk1);
        list.add(pk2);
        list.add(pk3);
        list.add(pk4);
        list.add(pk5);

        return list;
    }

    private List<PKDataInfo> createPKList2(){
        List<PKDataInfo> list = new ArrayList<>();
        float random = (float) Math.random();
        PKDataInfo pk1 = new PKDataInfo("用水量（立方米）",239*random,482*random);
        PKDataInfo pk2 = new PKDataInfo("用电量（千瓦）",123*random,150*random);
        PKDataInfo pk3 = new PKDataInfo("用煤量（立方米）",100*random,210*random);
        PKDataInfo pk4 = new PKDataInfo("用气量（立方米）",40*random,30*random);
        PKDataInfo pk5 = new PKDataInfo("货运量（吨）",30*random,40*random);

        list.add(pk1);
        list.add(pk2);
        list.add(pk3);
        list.add(pk4);
        list.add(pk5);

        return list;
    }

    private void stopPkAnimation(){
        tvPk.setVisibility(View.GONE);
    }

    private void startPkAnimation(){
        tvPk.setVisibility(View.VISIBLE);
        if (mAnimatorIn.isRunning()) {
            mAnimatorIn.end();
            mAnimatorIn.cancel();
        }

        mAnimatorIn.setTarget(tvPk);
        mAnimatorIn.start();

        startNumAnim();


    }

    private void startNumAnim(){
        double random = Math.random();
        NumAnim.startAnim(tvNum1, (float) (762*random));
        NumAnim.startAnim(tvNum2, (float) (524*random));
    }


    private Animator createAnimatorOut(Context context) {
        return AnimatorInflater.loadAnimator(context, mAnimatorResId);
    }

    private Animator createAnimatorIn(Context context) {
        Animator animatorIn;
        if (mAnimatorReverseResId == 0) {
            animatorIn = AnimatorInflater.loadAnimator(context, mAnimatorResId);
            animatorIn.setInterpolator(new ReverseInterpolator());
        } else {
            animatorIn = AnimatorInflater.loadAnimator(context, mAnimatorReverseResId);
        }
        return animatorIn;
    }

    private class ReverseInterpolator implements Interpolator {
        @Override public float getInterpolation(float value) {
            return Math.abs(1.0f - value);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initToolbar(Bundle bundle) {

    }
}
