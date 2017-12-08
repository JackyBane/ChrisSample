package com.example.chris.myapplication.dragger2.component;

import android.app.Activity;

import com.example.chris.myapplication.commom.Test1;
import com.example.chris.myapplication.dragger2.module.ActivityModule;
import com.example.chris.myapplication.dragger2.scopes.PerActivity;
import com.example.chris.myapplication.ui.activity.BaseActivity;

import dagger.Component;

@Component(modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();     //提供activity的上下文

    Test1 getTest();

    void inj(BaseActivity activity);
}
