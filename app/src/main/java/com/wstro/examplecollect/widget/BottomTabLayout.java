/*
package com.wstro.examplecollect.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wstro.app.common.utils.LogUtil;
import com.wstro.examplecollect.R;

import java.util.ArrayList;

*/
/**
 * ClassName: BottomTabLayout
 * Function:
 * Date:     2017/11/28 0028 13:35
 *
 * @author pengl
 * @see
 *//*

public class BottomTabLayout extends LinearLayout {
    private static final int DEFAULT_SELECTED_COLOR = 0xFFFE5977;

    private static final int DEFAULT_UNSELECTED_COLOR = 0xFF666666;

    private static final int DEFAULT_TEXT_SIZE = 12; //sp

    private static final int DEFAULT_HEIGHT_WITH_ICON = 20; // dps

    private static final int DEFAULT_GAP_TEXT = 4; // dps

    private static final int DEFAULT_HEIGHT = 52; // dps

    private static final int DEFAULT_WIDTH_MSG = 46; // dps

    private final ArrayList<Tab> tabList = new ArrayList<>();

    private Tab selectedTab;

    */
/**
     * tab背景色
     *//*

    private int tabBackgroundColor;

    */
/**
     * tab高度
     *//*

    private int tabBarHeight;

    */
/**
     * Tab选中的文字颜色，默认颜色#fe5977
     *//*

    private int selectedColor ;

    */
/**
     * Tab未选中的文字颜色，默认颜色#666666
     *//*

    private int unSelectedColor;

    */
/**
     * 文字尺寸
     *//*

    private int fontSize;

    */
/**
     * tab的图片宽高
     *//*

    private int imgWidth,imgHeight;


    */
/**
     * tab的文字顶部间距
     *//*

    private int tabTextMarginTop;


    */
/**
     * tab的消息布局宽度
     *//*

    private int tabMsgWidth;

    */
/**
     * tab的消息布局顶部间距
     *//*

    private int tabMsgMarginTop;


    public interface OnTabSelectedListener {
        void OnTabSelected(int position);
    }

    private OnTabSelectedListener onTabSelectedListener;


    public BottomTabLayout(Context context) {
        this(context, null);
    }

    public BottomTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(context,attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BottomTabBar);
        if(array != null){
            tabBackgroundColor = array.getColor(R.styleable.BottomTabLayout_tab_bar_background, Color.WHITE);
            selectedColor = array.getColor(R.styleable.BottomTabLayout_tab_selected_color, DEFAULT_SELECTED_COLOR);
            unSelectedColor = array.getColor(R.styleable.BottomTabLayout_tab_unselected_color, DEFAULT_UNSELECTED_COLOR);

            tabBarHeight = array.getDimensionPixelSize(R.styleable.BottomTabLayout_tab_bar_height, DEFAULT_HEIGHT);
            fontSize = array.getDimensionPixelSize(R.styleable.BottomTabLayout_tab_font_size, DEFAULT_TEXT_SIZE);
            imgWidth = array.getDimensionPixelSize(R.styleable.BottomTabLayout_tab_img_width, DEFAULT_HEIGHT_WITH_ICON);
            imgHeight = array.getDimensionPixelSize(R.styleable.BottomTabLayout_tab_img_height, DEFAULT_HEIGHT_WITH_ICON);

            tabTextMarginTop = array.getDimensionPixelSize(R.styleable.BottomTabLayout_tab_text_margin_top, DEFAULT_GAP_TEXT);
            tabMsgMarginTop = array.getDimensionPixelSize(R.styleable.BottomTabLayout_tab_msg_margin_top,DEFAULT_GAP_TEXT);
            tabMsgWidth = array.getDimensionPixelSize(R.styleable.BottomTabLayout_tab_msg_width, DEFAULT_WIDTH_MSG);

            array.recycle();
        }

        initTabLayout();
    }

    private void initTabLayout(){
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                tabBarHeight);
        setLayoutParams(layoutParams);
        setBackgroundColor(tabBackgroundColor);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(heightMode == MeasureSpec.AT_MOST){
            heightSize = Math.max(heightSize,tabBarHeight);
        }

        setMeasuredDimension(widthMeasureSpec,MeasureSpec.makeMeasureSpec(heightSize,heightMode));
    }

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogUtil.d("width:"+w+",height:"+h);
    }

    public void addTab(Tab tab){
        addTab(tab, tabList.isEmpty());
    }

    public void addTab(Tab tab, int position){
        addTab(tab, position, tabList.isEmpty());
    }

    public void addTab(Tab tab, boolean isSelected){
        addTab(tab, tabList.size(), isSelected);
    }

    public void addTab(Tab tab, int position, boolean isSelected){
        if (tab.parent != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }

        configureTab(tab, position);

        if (isSelected) {
            tab.select();
        }
    }

    private void configureTab(Tab tab, int position) {
        tab.setPosition(position);
        tabList.add(position, tab);

        final int count = tabList.size();
        for (int i = position + 1; i < count; i++) {
            tabList.get(i).setPosition(i);
        }
    }

    public void setSelectedTab(int position) {
        if (position < 0 || position >= tabList.size())
            return;

        Tab tab = tabList.get(position);
        if (selectedTab == tab) {
            return;
        }

        selectedTab = tab;
        for (int i = 0; i < tabList.size(); i++) {
            tabList.get(i).getTabView().update();
        }

        if (onTabSelectedListener != null) {
            onTabSelectedListener.OnTabSelected(selectedTab.getPosition());
        }
    }

    public void setSelectedTab(Tab tab){
        setSelectedTab(tab.getPosition());
    }

    public static final class Tab{
        private Object tag;
        private Drawable selectIcon,unSelectIcon;
        private CharSequence text;
        private int position = -1;

        private TabView tabView;

        private BottomTabLayout parent;

        public Object getTag() {
            return tag;
        }

        public void setTag(Object tag) {
            this.tag = tag;
        }

        public Drawable getSelectIcon() {
            return selectIcon;
        }

        public void setSelectIcon(Drawable selectIcon) {
            this.selectIcon = selectIcon;
        }

        public Drawable getUnSelectIcon() {
            return unSelectIcon;
        }

        public void setUnSelectIcon(Drawable unSelectIcon) {
            this.unSelectIcon = unSelectIcon;
        }

        public CharSequence getText() {
            return text;
        }

        public void setText(CharSequence text) {
            this.text = text;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public void select(){
            parent.setSelectedTab(this);
        }

        public boolean isSelected() {
            if (parent == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return parent.getSelectedTabPosition() == position;
        }

        public BottomTabLayout getParent() {
            return parent;
        }

        public void setParent(BottomTabLayout parent) {
            this.parent = parent;
        }

        public TabView getTabView() {
            return tabView;
        }

        public void setTabView(TabView tabView) {
            this.tabView = tabView;
        }
    }

    public int getTabCount() {
        return tabList.size();
    }

    public Tab getTabAt(int index) {
        return (index < 0 || index >= getTabCount()) ? null : tabList.get(index);
    }

    public int getSelectedTabPosition() {
        return selectedTab != null ? selectedTab.getPosition() : -1;
    }

    class TabView {
        private Tab tab;

        private ImageView iconView;
        private TextView textView;
        private FrameLayout msgLayout;
        private View msgView;
        private View itemView;

        private Context context;

        public TabView(Context context){
            itemView = LayoutInflater.from(context).inflate(R.layout.bottom_tab_bar_item,null);

            msgLayout = (FrameLayout) itemView.findViewById(R.id.fl_msg_layout);
            msgView = itemView.findViewById(R.id.iv_msg);
            iconView = (ImageView) itemView.findViewById(R.id.tab_bar_img);
            textView = (TextView) itemView.findViewById(R.id.tab_bar_tv);

            LayoutParams lp = ((LayoutParams) iconView.getLayoutParams());
            lp.width = imgWidth;
            lp.height = imgHeight;
            iconView.setLayoutParams(lp);

            ViewGroup.MarginLayoutParams textParams = (MarginLayoutParams) textView.getLayoutParams();
            textParams.topMargin = tabTextMarginTop;
            textView.setLayoutParams(textParams);

            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,fontSize);
        }

        public void setTab(Tab tab) {
            if(this.tab != tab){
                this.tab = tab;
                update();
            }
        }

        private void update() {
            updateTextAndIcon(textView,iconView);

            setSelectedTab(tab);
        }

        private void updateTextAndIcon(TextView textView, ImageView iconView){
            if (iconView != null) {
                iconView.setImageDrawable(tab.isSelected() ? tab.getSelectIcon() : tab.getUnSelectIcon());
            }

            if (textView != null) {
                textView.setText(tab.getText());
                textView.setTextColor(tab.isSelected() ? selectedColor : unSelectedColor);
            }
        }
    }


}
*/
