package com.example.chris.myapplication.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.chris.myapplication.R;
import com.example.chris.myapplication.commom.Test;
import com.example.chris.myapplication.commom.Test1;
import com.example.chris.myapplication.dragger2.component.DaggerMainActivityComponent;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/7 17:22
 */

public class MainActivity extends BaseActivity {


    @Bind(R.id.tv_poetry)
    TextView tvPoetry;
    @Bind(R.id.btn)
    Button btn;

    @Inject
    Activity activity;

    @Inject
    Test test;


    @Override
    protected int initUI() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        DaggerMainActivityComponent.builder().activityComponent(getActivityComponent()).build().inject(this);
    }

    @Override
    protected void initListener() {

    }



    @OnClick(R.id.btn)
    public void onViewClicked() {
        openActivity(OneActivity.class);
    }
}
