package com.wstro.examplecollect.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * ClassName: TranslucentScrollView
 * Function:
 * Date:     2017/9/13 0013 17:22
 *
 * @author Administrator
 * @see
 */
public class TranslucentScrollView2 extends ScrollView {
    public interface TranslucentListener {
        /**
         * 透明度的监听
         *
         * @param alpha 0~1透明度
         */
        void onTranslucentChanged(float alpha);
    }

    private TranslucentListener listener;

    public void setTranslucentListener(TranslucentListener listener) {
        this.listener = listener;
    }



    public TranslucentScrollView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null) {
            int scrollY = getScrollY();
            int screenHeight = getContext().getResources().getDisplayMetrics().heightPixels;
            if (scrollY <= screenHeight / 3f) {//0~1f,而透明度应该是1~0f
                listener.onTranslucentChanged(1 - scrollY / (screenHeight / 3f));//alpha=滑出去的高度/(screen_height/3f)
            }
        }
    }
}
