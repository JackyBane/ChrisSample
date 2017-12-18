package com.example.chris.myapplication.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chris.myapplication.R;
import com.example.chris.myapplication.adapter.CourseListAdapter;
import com.example.chris.myapplication.dragger2.component.DaggerMainActivityComponent;
import com.example.chris.myapplication.entity.CourseInfo;
import com.example.chris.myapplication.ui.base.BaseActivity;
import com.example.chris.myapplication.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/7 17:22
 */

public class MainActivity extends BaseActivity {


    private List<CourseInfo> list = new ArrayList<>();
    private CourseListAdapter courseListAdapter;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }
}
