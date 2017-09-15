package com.wstro.examplecollect.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * ClassName: CustomProgressBar
 * Function:
 * Date:     2017/9/14 0014 18:24
 *
 * @author Administrator
 * @see
 */
public class CustomProgressBar extends View {
    Paint paint;

    private int width;
    private String str = "75%";

    Rect bounds;

    public CustomProgressBar(Context context) {
        super(context);
        init();
    }



    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);

        bounds = new Rect();
        paint.setTextSize(32);
        paint.getTextBounds(str,0,str.length(),bounds);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.parseColor("#6F83AB"));
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);

        RectF oval=new RectF();                     //RectF对象
        oval.left=0;                              //左边
        oval.top=0;                                   //上边
        oval.right=width - 40;                             //右边
        oval.bottom=width - 40;                                //下边
        canvas.drawArc(oval,270,0,false,paint);

        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#dddddd"));
        //canvas.drawArc(oval,0,360,false,paint);
        canvas.drawCircle(width/2,width/2,width/2,paint);

         /*
         * 控件宽度/2 - 文字宽度/2
         */
        float startX = getWidth() / 2 - bounds.width() / 2;

        /*
         * 控件高度/2 + 文字高度/2,绘制文字从文字左下角开始,因此"+"
         */
        float startY = getHeight() / 2 + bounds.height() / 2;

        // 绘制文字
        paint.setColor(Color.parseColor("#6F83AB"));
        paint.setTextSize(32);
        canvas.drawText(str, startX, startY, paint);
    }
}
