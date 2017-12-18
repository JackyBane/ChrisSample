package com.example.chris.myapplication.dragger2.component;

import com.example.chris.myapplication.dragger2.module.FragmentModule;
import com.example.chris.myapplication.ui.base.BaseFragment;

import dagger.Component;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/7 19:27
 */
@Component(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(BaseFragment baseFragment);
}
