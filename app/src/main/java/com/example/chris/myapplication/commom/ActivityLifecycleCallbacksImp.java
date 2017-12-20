package com.example.chris.myapplication.commom;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * 类描述：ActivityLifecycleCallbacks默认实现
 * 创建人 Chris
 * 创建时间：2017/12/20 15:12
 */

public class ActivityLifecycleCallbacksImp implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }
}
