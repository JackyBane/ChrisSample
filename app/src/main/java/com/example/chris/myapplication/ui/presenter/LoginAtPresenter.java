package com.example.chris.myapplication.ui.presenter;


import android.text.TextUtils;

import com.example.chris.myapplication.api.ApiRetrofit;
import com.example.chris.myapplication.api.model.response.BaseResponse;
import com.example.chris.myapplication.api.model.response.LoginResponse;
import com.example.chris.myapplication.ui.base.BaseActivity;
import com.example.chris.myapplication.ui.base.BasePresenter;
import com.example.chris.myapplication.ui.view.ILoginAtView;
import com.example.chris.myapplication.utils.ToastUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LoginAtPresenter extends BasePresenter<ILoginAtView> {

    public LoginAtPresenter(BaseActivity context) {
        super(context);
    }

    public void login() {
//        mContext.openActivityAndCloseThis(MainActivity.class);

        String phone = getView().getEtPhone().getText().toString().trim();
        String pwd = getView().getEtPwd().getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showToast("号码不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.showToast("密码不能为空");
            return;
        }

        ApiRetrofit.getInstance()
                .login(phone, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseResponse<LoginResponse>>() {
                    @Override
                    public void call(BaseResponse<LoginResponse> loginResponse) {
                        int code = loginResponse.getCode();
                        if (code == 200) {
                            ToastUtils.showToast("登录成功");
                        } else {
                            ToastUtils.showToast("登录失败");
                        }
                    }
                });


    }

    private void loginError(Throwable throwable) {

    }
}
