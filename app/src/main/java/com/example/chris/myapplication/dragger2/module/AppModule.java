package com.example.chris.myapplication.dragger2.module;

import android.content.Context;


import com.example.chris.myapplication.utils.ToastUtils;
import com.example.mylibrary.utils.Logger;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides @Singleton
    public Context provideContext(){
        return context;
    }



}
