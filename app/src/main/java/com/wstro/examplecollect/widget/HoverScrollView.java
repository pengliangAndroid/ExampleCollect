package com.wstro.examplecollect.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * ClassName: HoverScrollView
 * Function:
 * Date:     2017/10/10 0010 10:16
 *
 * @author Administrator
 * @see
 */
public class HoverScrollView extends ScrollView {
    private View topView;
    private View flowView;

    public HoverScrollView(Context context) {
        super(context);
    }

    public HoverScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HoverScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if(topView != null){
            if(t >= topView.getTop()){
                flowView.setVisibility(VISIBLE);
            }else{
                flowView.setVisibility(GONE);
            }
        }
    }

    public void setTopView(View topView) {
        this.topView = topView;
    }

    public void setFlowView(View flowView) {
        this.flowView = flowView;
    }
}
