package com.example.chris.myapplication.presenter.activity;

import com.example.chris.myapplication.api.model.response.BaseResponse;
import com.example.chris.myapplication.ui.IView;
import com.example.chris.myapplication.ui.activity.LoginActivity;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/20 9:43
 */

public class LoginActivityPresenter extends BasePresenter<LoginActivity> {

    public LoginActivityPresenter(LoginActivity loginActivity) {
        this.view= loginActivity;
    }

    @Override
    protected void parserData(BaseResponse data) {

    }


}
