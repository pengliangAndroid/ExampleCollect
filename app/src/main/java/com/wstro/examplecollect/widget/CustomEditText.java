package com.wstro.examplecollect.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.wstro.examplecollect.AppData;


public class CustomEditText extends EditText {

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomEditText(Context context) {
        this(context,null);
    }

    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(AppData.get().getTextTypeface(),style);
    }

    public void setTypeface(Typeface tf) {
        super.setTypeface(AppData.get().getTextTypeface());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}