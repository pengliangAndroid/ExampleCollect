package com.wstro.examplecollect.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.wstro.app.common.utils.DeviceUtils;
import com.wstro.examplecollect.R;


public class ScoreProgressView extends View {

    private int firstColor,secondColor;

    private float firstVal,secondVal;

    private int width,height;

    private int trigonWidth = 10;

    private int intervalWidth = 10;


    public ScoreProgressView(Context context) {
        super(context);
        init(context, null);
    }

    public ScoreProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ScoreProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ScoreProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        firstColor = Color.parseColor("#0c56a5");
        secondColor = Color.parseColor("#f16666");

        handleTypedArray(context,attrs);

    }


    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScoreProgressView);
        firstVal = typedArray.getFloat(R.styleable.ScoreProgressView_val1,0);
        secondVal = typedArray.getFloat(R.styleable.ScoreProgressView_val2,0);

        typedArray.recycle();
    }

    public void setValue(float v1,float v2){
        firstVal = v1;
        secondVal = v2;

        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if(heightMode == MeasureSpec.AT_MOST){
            height = DeviceUtils.dp2px(getContext(),5);
            trigonWidth = height;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 创建画笔
        Paint p = new Paint();
        //设置实心
        p.setStyle(Paint.Style.FILL);
        // 设置红色
        p.setColor(firstColor);
        // 设置画笔的锯齿效果
        p.setAntiAlias(true);

        float firstWidth = firstVal * (width / (firstVal + secondVal)) - intervalWidth;
        //绘制矩形
        canvas.drawRect(0, 0, firstWidth, height, p);

        //再绘制三角形
        Path path = new Path();
        path.moveTo(firstWidth,0);
        path.lineTo(firstWidth + trigonWidth,height);
        path.lineTo(firstWidth,height);
        path.close();

        canvas.drawPath(path,p);

        float offsetX = firstWidth + 2*intervalWidth;


        //绘制矩形
        p.setColor(secondColor);
        //先绘制三角形
        path = new Path();
        path.moveTo(offsetX,0);
        path.lineTo(offsetX + trigonWidth,height);
        path.lineTo(offsetX + trigonWidth,0);
        path.close();
        canvas.drawPath(path,p);

        offsetX = offsetX + trigonWidth;

        //绘制矩形
        //float secondWidth = secondVal * (width / (firstVal + secondVal)) - intervalWidth;
        canvas.drawRect(offsetX, 0, width, height, p);
    }
}

