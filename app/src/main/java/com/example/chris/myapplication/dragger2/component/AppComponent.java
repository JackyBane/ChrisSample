package com.example.chris.myapplication.dragger2.component;

import android.content.Context;

import com.example.chris.myapplication.dragger2.module.AppModule;
import com.example.chris.myapplication.utils.ToastUtils;

import java.util.logging.Logger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    Context getContext();       //提供application的上下文(暴露出去)

}
