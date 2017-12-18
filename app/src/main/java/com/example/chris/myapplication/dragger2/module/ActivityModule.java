package com.example.chris.myapplication.dragger2.module;

import android.app.Activity;

import com.example.chris.myapplication.commom.Test1;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    public Activity provideActivity(){
        return activity;
    }


    @Provides
    public Test1 provideTest1(){
        return new Test1(activity);
    }

}
