package com.example.chris.myapplication;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.example.chris.myapplication.commom.ActivityLifecycleCallbacksImp;
import com.example.chris.myapplication.utils.ToastUtils;
import com.example.mylibrary.utils.Utils;

import org.litepal.LitePalApplication;


/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/6 18:31
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        //初始化工具类，可以用这个返回application实例和记录Activiyt开启的个数，便于一键退出
        Utils.init(this);
        ToastUtils.init(this);
//        AutoLayoutConifg.getInstance().useDeviceSize();
        //LitePal初始化
        LitePalApplication.initialize(this);
        //异步初始化配置Service，便于加快启动速度
//        InitConfigService.start(this);

    }

}
