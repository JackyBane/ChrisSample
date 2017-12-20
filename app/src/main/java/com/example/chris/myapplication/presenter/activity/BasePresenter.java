package com.example.chris.myapplication.presenter.activity;

import android.util.Log;

import com.example.chris.myapplication.api.MyApi;
import com.example.chris.myapplication.api.base.BaseApiRetrofit;
import com.example.chris.myapplication.api.model.BaseResponse;
import com.example.chris.myapplication.ui.IView;
import com.example.mylibrary.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 类描述：业务层公共部分代码封装
 * 创建人 Chris
 * 创建时间：2017/12/7 17:22
 */

public abstract class BasePresenter<T extends IView> {
    protected String TAG = this.getClass().getSimpleName();

    protected Logger logger = Logger.getLogger();

    protected T view;

    protected MyApi mApi;

    public BasePresenter() {

        if (mApi == null) {
            // 第一次初始化完成后，所有子类都可以使用
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            //在构造方法中完成对Retrofit接口的初始化
            mApi = new Retrofit.Builder()
                    .baseUrl(MyApi.BASE_URL)
                    .client(new BaseApiRetrofit().getClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
                    .create(MyApi.class);
        }

    }

    public class MySubscriber extends Subscriber<BaseResponse>{

        @Override
        public void onCompleted() {
            Log.e(TAG, "onCompleted: "+"------" );
        }

        @Override
        public void onError(Throwable e) {
            failed(e.toString());
        }

        @Override
        public void onNext(BaseResponse data) {
            if (data.error_code==0) {
                parserData(data);
            }else {
                failed(data.reason);
            }
        }
    }

    //处理数据，单个网络请求，多个连续的网络请求请自行处理
    public void handleData(Observable observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber());
    }


    /**
     * 错误处理,子类可重写实现自己的处理方式
     *
     * @param msg
     */
    protected void failed(String msg){
        view.failed(msg);
    }

    /**
     * 解析服务器回复数据
     *
     * @param data
     */
    protected abstract void parserData(BaseResponse data);
}
