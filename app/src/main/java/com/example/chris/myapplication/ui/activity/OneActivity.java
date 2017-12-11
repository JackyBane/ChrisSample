package com.example.chris.myapplication.ui.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.chris.myapplication.R;

import butterknife.Bind;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/8 9:00
 */

public class OneActivity extends BaseActivity{



    @Bind(R.id.video_layout)
    FrameLayout videoLayout;
    @Bind(R.id.fl_bottom_layout)
    FrameLayout flBottomLayout;

    private static final String VIDEO_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    @Override
    protected int initUI() {
        return R.layout.activity_one;
    }


    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {

    }


}
