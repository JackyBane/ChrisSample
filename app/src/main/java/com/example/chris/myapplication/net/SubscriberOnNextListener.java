package com.example.chris.myapplication.net;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/8 16:39
 */

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
