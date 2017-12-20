package com.example.chris.myapplication.ui.activity;

import android.util.Log;

import com.example.chris.myapplication.R;
import com.example.chris.myapplication.presenter.activity.LoginActivityPresenter;
import com.example.chris.myapplication.ui.base.BaseActivity;
import com.example.chris.myapplication.utils.ToastUtils;

import rx.Observable;


/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/20 9:41
 */

public class LoginActivity extends BaseActivity {

    private LoginActivityPresenter loginActivityPresenter=new LoginActivityPresenter(this);

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        Log.e(TAG, "initData: "+"hhhhhhhhhhh" );
    }
}
