package com.example.chris.myapplication.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.chris.myapplication.R;
import com.example.chris.myapplication.adapter.CourseListAdapter;
import com.example.chris.myapplication.api.ApiRetrofit;
import com.example.chris.myapplication.api.model.response.BaseResponse;
import com.example.chris.myapplication.api.model.response.CardRe;
import com.example.chris.myapplication.entity.CourseInfo;
import com.example.chris.myapplication.presenter.activity.MainAcitivityPresenter;
import com.example.chris.myapplication.ui.base.BaseActivity;
import com.example.chris.myapplication.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/7 17:22
 */

public class MainActivity extends BaseActivity {

    private MainAcitivityPresenter mainActivityPresenter=new MainAcitivityPresenter(this);

    @Bind(R.id.tv_content)
    TextView tvContent;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mainActivityPresenter.getData("024405d8599bbb32f0ab207983ac559b");
    }

    @Override
    public void success(Object o) {
        tvContent.setText(((CardRe)o)._$2);
    }

    public void show() {
        ToastUtils.showToast("---------------------------------------------");
    }


}
