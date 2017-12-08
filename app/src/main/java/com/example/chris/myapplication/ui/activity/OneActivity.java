package com.example.chris.myapplication.ui.activity;

import android.app.Activity;

import com.example.chris.myapplication.R;

import javax.inject.Inject;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/8 9:00
 */

public class OneActivity extends BaseActivity {
    @Inject
    Activity activity;

    @Override
    protected int initUI() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        logger.e(activity.toString());
    }

    @Override
    protected void initListener() {

    }
}
