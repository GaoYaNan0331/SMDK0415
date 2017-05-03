package com.example.administrator.qq_login0420;

import android.app.Application;

import org.xutils.x;

/**
 * data:2017/4/20
 * author:高亚男(Administrator)
 * function:
 */

public class MyAplaction extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.http();
    }
}
