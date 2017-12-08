package com.example.chris.myapplication;

import android.app.Application;

import com.example.chris.myapplication.dragger2.component.AppComponent;
import com.example.chris.myapplication.dragger2.component.DaggerAppComponent;
import com.example.chris.myapplication.dragger2.module.AppModule;
import com.example.chris.myapplication.utils.ToastUtils;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/6 18:31
 */

public class App extends Application {

    private AppComponent appComponent;

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        initApplication();
        init();
    }

    private void init() {
        ToastUtils.init(this);
        //异步初始化配置Service，便于加快启动速度
//        InitConfigService.start(this);

    }

    private void initApplication() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent ;
    }

    public static App getApp() {
        return app;
    }
}
