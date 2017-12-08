package com.example.chris.myapplication.dragger2.component;

import com.example.chris.myapplication.dragger2.module.MainActivityModule;
import com.example.chris.myapplication.ui.activity.MainActivity;

import dagger.Component;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/8 9:29
 */
@Component(dependencies = ActivityComponent.class,modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
