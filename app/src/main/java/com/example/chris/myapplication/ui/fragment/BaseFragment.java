package com.example.chris.myapplication.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chris.myapplication.dragger2.component.DaggerFragmentComponent;
import com.example.chris.myapplication.dragger2.module.FragmentModule;
import com.example.chris.myapplication.ui.activity.BaseActivity;
import com.example.mylibrary.utils.Logger;

import javax.inject.Inject;

/**
 * 类描述：fragment的基类
 * 创建人：Chris
 * 创建时间：2017/3/7 17:37
 */
public abstract class BaseFragment extends Fragment {

    @Inject
    protected Activity activity;

    protected String TAG = this.getClass().getSimpleName();

    protected Logger logger= Logger.getLogger();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(getActivity()))
                .build()
                .inject(this);
    }

    // 初始化Fragment布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    //根据情况选择重写
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container);
    }

    //根据情况选择重写
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return initView(inflater);
    }

    /**
     * @param inflater
     */
    public abstract View initView(LayoutInflater inflater);


    /**
     * 初始化数据, 子类可以不实现
     */
    protected void initData() {

    }

    protected void initListener() {

    }


    /**
     * Fragment替换(核心为隐藏当前的,显示现在的,用过的将不会destrory与create)
     * 注意这里使用的是getChildFragmentManager获取的FragmentManager
     */
    protected Fragment currentFragment;

    protected void smartFragmentReplace(int target, Fragment toFragment) {
        FragmentManager manager = getChildFragmentManager();        //这里要注意
        FragmentTransaction transaction = manager.beginTransaction();
        // 如有当前在使用的->隐藏当前的
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        String toClassName = toFragment.getClass().getSimpleName();
        // toFragment之前添加使用过->显示出来
        if (manager.findFragmentByTag(toClassName) != null) {
            transaction.show(toFragment);
        } else {// toFragment还没添加使用过->添加上去
            transaction.add(target, toFragment, toClassName);
        }
        transaction.commit();
        // toFragment更新为当前的
        currentFragment = toFragment;
    }


    /**
     * Fragment替换(当前destroy,新的create)
     */
    protected void fragmentReplace(@IdRes int target, Fragment toFragment, boolean backStack) {
        FragmentManager manager = getChildFragmentManager();            //注意这里的manager
        FragmentTransaction transaction = manager.beginTransaction();
        String toClassName = toFragment.getClass().getSimpleName();
        if (manager.findFragmentByTag(toClassName) == null) {
            transaction.replace(target, toFragment, toClassName);
            if (backStack) {
                transaction.addToBackStack(toClassName);
            }
            transaction.commit();
        }
    }


}
