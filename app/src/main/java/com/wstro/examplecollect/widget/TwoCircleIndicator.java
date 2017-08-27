package com.wstro.examplecollect.widget;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;
import com.wstro.app.common.utils.DeviceUtils;
import com.wstro.examplecollect.R;


public class TwoCircleIndicator extends LinearLayout {

    private final static int DEFAULT_INDICATOR_WIDTH = 5;

    private RecyclerViewPager mViewpager;

    private int mIndicatorMargin = -1;
    private int mIndicatorWidth = -1;
    private int mIndicatorHeight = -1;

    private int mIndicatorBackgroundResId = R.drawable.blue_ring_shape;
    private int mIndicatorUnselectedBackgroundResId = R.drawable.gray_ring_shape;

    /*private int mAnimatorResId = R.animator.scale_with_alpha;
    private int mAnimatorReverseResId = 0;

    private Animator mAnimatorOut,mAnimatorOut1;
    private Animator mAnimatorIn,mAnimatorIn1;
    private Animator mImmediateAnimatorOut;
    private Animator mImmediateAnimatorIn;*/

    private int mLastPosition = -1;

    public TwoCircleIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public TwoCircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TwoCircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TwoCircleIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //handleTypedArray(context, attrs);
        //checkIndicatorConfig(context);

        mIndicatorMargin = DeviceUtils.dp2px(context,DEFAULT_INDICATOR_WIDTH);
        mIndicatorWidth = mIndicatorMargin;
        mIndicatorHeight = mIndicatorMargin;

        setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);

       /* mAnimatorResId = (mAnimatorResId == 0) ? R.animator.scale_with_alpha : mAnimatorResId;

        mAnimatorOut = createAnimatorOut(context);
        mAnimatorOut1 = createAnimatorOut(context);
        mImmediateAnimatorOut = createAnimatorOut(context);
        mImmediateAnimatorOut.setDuration(0);

        mAnimatorIn = createAnimatorIn(context);
        mAnimatorIn1 = createAnimatorIn(context);
        mImmediateAnimatorIn = createAnimatorIn(context);
        mImmediateAnimatorIn.setDuration(0);*/
    }

   /* private Animator createAnimatorOut(Context context) {
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
    }*/


    public void setViewPager(RecyclerViewPager viewPager) {
        mViewpager = viewPager;
        if (mViewpager != null && mViewpager.getAdapter() != null) {
            mLastPosition = -1;
            createIndicators();
            mViewpager.removeOnPageChangedListener(mInternalPageChangeListener);
            mViewpager.addOnPageChangedListener(mInternalPageChangeListener);
            mInternalPageChangeListener.OnPageChanged(-1,mViewpager.getCurrentPosition());
        }
    }

    private final RecyclerViewPager.OnPageChangedListener mInternalPageChangeListener = new RecyclerViewPager.OnPageChangedListener() {

        @Override
        public void OnPageChanged(int fromPosition, int position) {
            if (mViewpager.getAdapter() == null || mViewpager.getAdapter().getItemCount() <= 0) {
                return;
            }

            /*if (mAnimatorIn.isRunning()) {
                mAnimatorIn.end();
                mAnimatorIn.cancel();
            }

            if (mAnimatorIn1.isRunning()) {
                mAnimatorIn1.end();
                mAnimatorIn1.cancel();
            }

            if (mAnimatorOut.isRunning()) {
                mAnimatorOut.end();
                mAnimatorOut.cancel();
            }

            if (mAnimatorOut1.isRunning()) {
                mAnimatorOut1.end();
                mAnimatorOut1.cancel();
            }*/

            View currentIndicator;
            if (mLastPosition >= 0 && (currentIndicator = getChildAt(mLastPosition)) != null) {
                currentIndicator.setBackgroundResource(mIndicatorUnselectedBackgroundResId);
                //mAnimatorIn.setTarget(currentIndicator);
                //mAnimatorIn.start();
            }

            if (mLastPosition >= 0 && (currentIndicator = getChildAt(mLastPosition + 1)) != null) {
                currentIndicator.setBackgroundResource(mIndicatorUnselectedBackgroundResId);
                //mAnimatorIn1.setTarget(currentIndicator);
                //mAnimatorIn1.start();
            }

            View selectedIndicator = getChildAt(position);
            if (selectedIndicator != null) {
                selectedIndicator.setBackgroundResource(mIndicatorBackgroundResId);
                //mAnimatorOut.setTarget(selectedIndicator);
                //mAnimatorOut.start();
            }

            selectedIndicator = getChildAt(position + 1);
            if (selectedIndicator != null) {
                selectedIndicator.setBackgroundResource(mIndicatorBackgroundResId);
                //mAnimatorOut1.setTarget(selectedIndicator);
                //mAnimatorOut1.start();
            }

            mLastPosition = position;
        }
    };

    private void setBackgroundResId(View indicatorView) {
        if (indicatorView != null) {
            indicatorView.setBackgroundResource(mIndicatorBackgroundResId);
            //mAnimatorOut.setTarget(indicatorView);
            //mAnimatorOut.start();
        }
    }

    /*public DataSetObserver getDataSetObserver() {
        return mInternalDataSetObserver;
    }

    private DataSetObserver mInternalDataSetObserver = new DataSetObserver() {
        @Override public void onChanged() {
            super.onChanged();
            if (mViewpager == null) {
                return;
            }

            int newCount = mViewpager.getAdapter().getCount();
            int currentCount = getChildCount();

            if (newCount == currentCount) {  // No change
                return;
            } else if (mLastPosition < newCount) {
                mLastPosition = mViewpager.getCurrentItem();
            } else {
                mLastPosition = -1;
            }

            createIndicators();
        }
    };*/

    /**
     * @deprecated User ViewPager addOnPageChangeListener
     */
    @Deprecated public void setOnPageChangeListener(RecyclerViewPager.OnPageChangedListener onPageChangeListener) {
        if (mViewpager == null) {
            throw new NullPointerException("can not find Viewpager , setViewPager first");
        }
        mViewpager.removeOnPageChangedListener(onPageChangeListener);
        mViewpager.addOnPageChangedListener(onPageChangeListener);
    }

    private void createIndicators() {
        removeAllViews();
        int count = mViewpager.getAdapter().getItemCount();
        if (count <= 0) {
            return;
        }
        int currentItem = mViewpager.getCurrentPosition();

        for (int i = 0; i < count; i++) {
            if (currentItem == i || (currentItem + 1) == i) {
                //addIndicator(mIndicatorBackgroundResId,mImmediateAnimatorOut);
                addIndicator(mIndicatorBackgroundResId,null);
            } else {
                //addIndicator(mIndicatorUnselectedBackgroundResId,mImmediateAnimatorIn);
                addIndicator(mIndicatorUnselectedBackgroundResId,null);
            }
        }
    }

    private void addIndicator(@DrawableRes int backgroundDrawableId,Animator animator) {
        /*if (animator.isRunning()) {
            animator.end();
            animator.cancel();
        }*/

        View indicator = new View(getContext());
        indicator.setBackgroundResource(backgroundDrawableId);
        addView(indicator, mIndicatorWidth, mIndicatorHeight);
        LayoutParams lp = (LayoutParams) indicator.getLayoutParams();
        lp.leftMargin = mIndicatorMargin;
        lp.rightMargin = mIndicatorMargin;
        indicator.setLayoutParams(lp);
    }

    private class ReverseInterpolator implements Interpolator {
        @Override public float getInterpolation(float value) {
            return Math.abs(1.0f - value);
        }
    }

    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

