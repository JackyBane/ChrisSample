package com.example.chris.myapplication.dragger2.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/7 19:46
 */
@Module
public class FragmentModule {

    private Activity activity;

    public FragmentModule(Activity activity) {
        this.activity=activity;
    }

    @Provides
    public Activity getActivity() {
        return activity;
    }
}
