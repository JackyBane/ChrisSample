package com.example.chris.myapplication.ui.activity;

import android.widget.EditText;

import com.example.chris.myapplication.R;
import com.example.chris.myapplication.ui.base.BaseActivity;
import com.example.chris.myapplication.ui.presenter.LoginAtPresenter;
import com.example.chris.myapplication.ui.view.ILoginAtView;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/18 16:57
 */

public class LoginActivity extends BaseActivity<ILoginAtView, LoginAtPresenter> implements ILoginAtView{
    @Override
    protected LoginAtPresenter createPresenter() {
        return new LoginAtPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public EditText getEtPhone() {
        return null;
    }

    @Override
    public EditText getEtPwd() {
        return null;
    }

    @Override
    public void initListener() {
        mPresenter.login();
    }
}
