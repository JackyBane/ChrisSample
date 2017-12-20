package com.example.chris.myapplication.presenter.activity;

import com.example.chris.myapplication.api.model.BaseResponse;
import com.example.chris.myapplication.api.model.CardRe;
import com.example.chris.myapplication.ui.activity.MainActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/19 18:01
 */

public class MainAcitivityPresenter extends BasePresenter<MainActivity>{

    public MainAcitivityPresenter(MainActivity mainAcitivity) {
        this.view = mainAcitivity;
    }

    @Override
    protected  void parserData(BaseResponse data) {
        view.success(data.getData());
    }


    public void getData(String key) {
        handleData(mApi.getCard(key));
    }
}
