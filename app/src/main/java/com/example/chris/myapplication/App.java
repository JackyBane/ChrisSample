package com.example.chris.myapplication;

import android.app.Application;

import com.example.chris.myapplication.utils.ToastUtils;
import com.example.mylibrary.utils.Utils;

import org.litepal.LitePalApplication;


/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/6 18:31
 */

public class App extends Application {


    private static App app;


    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        init();
    }

    private void init() {
        Utils.init(this);
        ToastUtils.init(this);
//        AutoLayoutConifg.getInstance().useDeviceSize();
        //LitePal初始化
        LitePalApplication.initialize(this);
        //异步初始化配置Service，便于加快启动速度
//        InitConfigService.start(this);

    }


    public static App getApp() {
        return app;
    }
}
