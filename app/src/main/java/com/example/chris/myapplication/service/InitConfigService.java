package com.example.chris.myapplication.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.example.mylibrary.cacheFileHelper.DiskLruCache;

import java.util.concurrent.TimeUnit;


/**
 * 类描述：开启异步初始化程序配置
 * 创建人 Chris
 * 创建时间：2017/8/9 11:26
 */

public class InitConfigService extends IntentService {

    private static final String ACTION_INIT_CONFIG="ACTION_INIT_CONFIG";

    /**
     * 文件硬盘缓存核心类。
     */
    private DiskLruCache mDiskLruCache;


    public InitConfigService() {
        super("initConfigService");
    }

    public static void start(Context context) {
        Intent intent=new Intent(ACTION_INIT_CONFIG);
        intent.setClass(context,InitConfigService.class);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        //这个可能初始化不是很及时，把一些不是需要立即初始化的过程放在这个里面
        //配置bugly
//        Bugly.init(getApplicationContext(), "d2ee016e72", true);

        //用来测试程序的运行的耗时情况
//        BlockDetectByPrinter.start();
        //用来处理未捕获的异常
//        CrashHandler.getInstance().init(this);

    }


}
