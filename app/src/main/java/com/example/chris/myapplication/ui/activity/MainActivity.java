package com.example.chris.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.chris.myapplication.R;
import com.example.chris.myapplication.adapter.CourseListAdapter;
import com.example.chris.myapplication.dragger2.component.DaggerMainActivityComponent;
import com.example.chris.myapplication.entity.CourseInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/7 17:22
 */

public class MainActivity extends BaseActivity {

    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.srl)
    SwipeRefreshLayout srl;


    private List<CourseInfo> list = new ArrayList<>();
    private CourseListAdapter courseListAdapter;


    @Override
    protected int initUI() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        DaggerMainActivityComponent.builder().activityComponent(getActivityComponent()).build().inject(this);
        for (int i = 0; i < 10; i++) {
            CourseInfo courseInfo = new CourseInfo();
            courseInfo.courseName = "本地教学";
            courseInfo.courseTime = "11:11";
            list.add(courseInfo);
        }

        //设置布局管理器
        rv.setLayoutManager(new LinearLayoutManager(this));
        courseListAdapter = new CourseListAdapter(R.layout.item_course, list);

        srl.postDelayed(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(false);
            }
        },3000);
        srl.setRefreshing(true);
        rv.setAdapter(courseListAdapter);
    }

    @Override
    protected void initListener() {
        courseListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                openActivity(OneActivity.class);
            }
        });
    }

}
