package com.example.chris.myapplication.dragger2.module;

import com.example.chris.myapplication.commom.Test;

import dagger.Module;
import dagger.Provides;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/8 9:28
 */
@Module
public class MainActivityModule {
    @Provides
    public Test getTest() {
        return new Test();
    }
}
