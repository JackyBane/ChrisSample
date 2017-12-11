package com.example.chris.myapplication.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.chris.myapplication.R;
import com.example.chris.myapplication.adapter.baseAdapter.CommonAdapter;
import com.example.chris.myapplication.adapter.baseAdapter.ViewHolder;
import com.example.chris.myapplication.entity.CourseInfo;

import java.util.List;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/11 12:18
 */

public class CourseListAdapter extends BaseQuickAdapter<CourseInfo,BaseViewHolder> {


    public CourseListAdapter(@LayoutRes int layoutResId, @Nullable List<CourseInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseInfo item) {
        helper.setText(R.id.tv_course_name,item.courseName);
        helper.setText(R.id.tv_course_time,item.courseTime);
    }
}
