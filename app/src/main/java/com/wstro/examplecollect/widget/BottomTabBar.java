package com.wstro.examplecollect.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wstro.app.common.utils.LogUtil;
import com.wstro.examplecollect.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BottomTabBar
 * Function:
 * Date:     2017/11/27 20:30
 *
 * @author pengl
 * @see
 */
public class BottomTabBar extends LinearLayout {

    public static final int DEFAULT_SELECTED_COLOR = Color.parseColor("#fe5977");

    public static final int DEFAULT_UNSELECTED_COLOR = Color.parseColor("#666666");

    /**
     *  tab的背景颜色
     */
    private int mTabBarBackgroundColor;

    /**
     * tab高度
     */
    private int mTabBarHeight;

    /**
     * Tab选中的文字颜色，默认颜色#fe5977
     */
    private int mSelectedColor ;

    /**
     * Tab未选中的文字颜色，默认颜色#666666
     */
    private int mUnSelectedColor;

    /**
     * 文字尺寸
     */
    private int mFontSize = 12;

    /**
     * tab的图片宽高
     */
    private int mImgWidth,mImgHeight;


    /**
     * tab的图片顶部间距
     */
    private int mTabImgMarginTop;


    /**
     * tab的消息布局宽度
     */
    private int mTabMsgWidth;

    /**
     * tab的消息布局顶部间距
     */
    private int mTabMsgMarginTop;

    private Context mContext;

    private LinearLayout mLayout;


    private List<ViewModel> mModelList;

    private int mCurIndex = -1;


    public interface OnTabChangeListener {
        void onTabChanged(int position);
    }

    private OnTabChangeListener listener;


    public BottomTabBar(Context context) {
        this(context, null);
    }

    public BottomTabBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context,attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        this.mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BottomTabBar);
        if(array != null){
            mTabBarBackgroundColor = array.getColor(R.styleable.BottomTabBar_tab_bar_background, Color.WHITE);
            mSelectedColor = array.getColor(R.styleable.BottomTabBar_tab_selected_color, DEFAULT_SELECTED_COLOR);
            mUnSelectedColor = array.getColor(R.styleable.BottomTabBar_tab_unselected_color, DEFAULT_UNSELECTED_COLOR);
            mTabBarHeight = (int) array.getDimension(R.styleable.BottomTabBar_tab_bar_height, dp2px(50));
            mFontSize = array.getDimensionPixelSize(R.styleable.BottomTabBar_tab_font_size,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14,getResources().getDisplayMetrics()));
            mImgWidth = (int) array.getDimension(R.styleable.BottomTabBar_tab_img_width, dp2px(24));
            mImgHeight = (int) array.getDimension(R.styleable.BottomTabBar_tab_img_height, dp2px(24));
            mTabImgMarginTop = (int) array.getDimension(R.styleable.BottomTabBar_tab_img_margin_top, dp2px(3));
            mTabMsgMarginTop = (int) array.getDimension(R.styleable.BottomTabBar_tab_msg_margin_top, dp2px(4));
            mTabMsgWidth = (int) array.getDimension(R.styleable.BottomTabBar_tab_msg_width, dp2px(46));

            array.recycle();
        }

        mModelList = new ArrayList<>();

        init();
    }

    private void init(){
        mLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.bottom_tab_bar,null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                mTabBarHeight);
        mLayout.setLayoutParams(layoutParams);
        mLayout.setBackgroundColor(mTabBarBackgroundColor);

        //addView(mLayout);
    }

    public BottomTabBar create(){
        addView(mLayout);

        return this;
    }


    public BottomTabBar addTabItem(String name, int imgIdSelect, int imgIdUnSelect) {
        return addTabItem(name, ContextCompat.getDrawable(mContext, imgIdSelect),
                ContextCompat.getDrawable(mContext, imgIdUnSelect));
    }

    public BottomTabBar addTabItem(String name, Drawable select,Drawable unSelect){
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.bottom_tab_bar_item, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,1.0f);
        itemView.setLayoutParams(lp);

        View msgLayout = itemView.findViewById(R.id.fl_msg_layout);
        ImageView msgView = (ImageView) itemView.findViewById(R.id.iv_msg);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.tab_bar_img);
        TextView textView = (TextView) itemView.findViewById(R.id.tab_bar_tv);


        imageView.setLayoutParams(new LinearLayout.LayoutParams(mImgWidth,mImgHeight));

        /*MarginLayoutParams marginParams = new MarginLayoutParams(mImgWidth,mImgHeight);
        marginParams.topMargin =  mTabImgMarginTop;
        imageView.setLayoutParams(marginParams);*/
        ViewGroup.MarginLayoutParams params = (MarginLayoutParams) imageView.getLayoutParams();
        params.topMargin = mTabImgMarginTop;
        LogUtil.d("topMargin:"+params.topMargin);
        imageView.setLayoutParams(params);

        imageView.setImageDrawable(unSelect);

        textView.setText(name);

        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,mFontSize);
        textView.setTextColor(mUnSelectedColor);

        msgLayout.setLayoutParams(new RelativeLayout.LayoutParams(mTabMsgWidth, RelativeLayout.LayoutParams.MATCH_PARENT));

        /*FrameLayout.MarginLayoutParams marginParams2 = new MarginLayoutParams(-1,-1);
        marginParams2.topMargin = mTabMsgMarginTop;
        msgView.setLayoutParams(marginParams2);*/


        mModelList.add(new ViewModel(name,select,unSelect,imageView,textView,msgLayout,msgView));

        itemView.setTag(mModelList.size() - 1);
        itemView.setOnClickListener(internalListener);
        mLayout.addView(itemView);
        return this;
    }


    public void setCurrentTab(int index){
        if (index == mCurIndex)
            return;

        mCurIndex = index;
        clearSelection();

        ViewModel model = mModelList.get(index);
        model.imageView.setImageDrawable(model.selectDrawable);
        model.textView.setTextColor(mSelectedColor);
        model.textView.setText(model.text);

        if(listener != null){
            listener.onTabChanged(index);
        }
    }



    /**
     * 清除掉所有的选中状态
     */
    private void clearSelection() {
        for (int i = 0; i < mModelList.size(); i++) {
            ViewModel model = mModelList.get(i);
            model.imageView.setImageDrawable(model.unSelectDrawable);
            model.textView.setTextColor(mUnSelectedColor);
        }
    }


    public BottomTabBar setOnTabChangeListener(OnTabChangeListener listener) {
        this.listener = listener;

        return this;
    }

    public BottomTabBar setTabBarBackgroundColor(int tabBarBackgroundColor) {
        this.mTabBarBackgroundColor = tabBarBackgroundColor;

        mLayout.setBackgroundColor(tabBarBackgroundColor);
        return this;
    }

    public BottomTabBar setTabBarHeight(int tabBarHeight) {
        this.mTabBarHeight = tabBarHeight;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                (int) tabBarHeight);
        mLayout.setLayoutParams(layoutParams);

        return this;
    }

    public BottomTabBar setTextColor(int selectedColor,int unSelectedColor) {
        this.mSelectedColor = selectedColor;
        this.mUnSelectedColor = unSelectedColor;
        return this;
    }

    public BottomTabBar setFontSize(int fontSize) {
        this.mFontSize = fontSize;
        return this;
    }

    public BottomTabBar setImgSize(int imgWidth,int imgHeight) {
        this.mImgWidth = imgWidth;
        this.mImgHeight = imgHeight;
        return this;
    }

    public BottomTabBar setTabImgMarginTop(int tabImgMarginTop) {
        this.mTabImgMarginTop = tabImgMarginTop;
        return this;
    }

    public BottomTabBar setTabMsgWidth(int tabMsgWidth) {
        this.mTabMsgWidth = tabMsgWidth;
        return this;
    }

    public BottomTabBar setTabMsgMarginTop(int tabMsgMarginTop) {
        this.mTabMsgMarginTop = tabMsgMarginTop;

        return this;
    }

    public int getCurIndex() {
        return mCurIndex;
    }

    public View getMsgLayout(int index){
        return mModelList.get(index).msgLayout;
    }

    public View getMsgView(int index){
        return mModelList.get(index).msgView;
    }

    private int dp2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public int sp2px(float spValue) {
        float scale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    private View.OnClickListener internalListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Integer index = (Integer) v.getTag();

            setCurrentTab(index);
        }
    };

    static class ViewModel{
        public String text;
        public Drawable selectDrawable;
        public Drawable unSelectDrawable;
        public ImageView imageView;
        public TextView textView;
        public View msgLayout;
        public ImageView msgView;

        public ViewModel(String text, Drawable selectDrawable, Drawable unSelectDrawable,
                         ImageView imageView, TextView textView, View msgLayout, ImageView msgView) {
            this.text = text;
            this.selectDrawable = selectDrawable;
            this.unSelectDrawable = unSelectDrawable;
            this.imageView = imageView;
            this.textView = textView;
            this.msgLayout = msgLayout;
            this.msgView = msgView;
        }
    }

}
