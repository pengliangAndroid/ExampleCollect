package com.wstro.examplecollect;

import android.content.Context;
import android.graphics.Typeface;

/**
 * ClassName: AppData
 * Function:
 * Date:     2017/9/13 0013 14:45
 *
 * @author Administrator
 * @see
 */
public class AppData {
    private static AppData instance;

    private Typeface textTypeface;

    private AppData(){}

    public static AppData get() {
        if (instance == null) {
            synchronized (AppData.class) {
                if(instance == null) {
                    instance = new AppData();
                }
            }
        }

        return instance;
    }

    public void initTypeface(Context context) {
        textTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/llhj.ttf");
    }

    public Typeface getTextTypeface() {
        return textTypeface;
    }
}
