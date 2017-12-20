package com.example.chris.myapplication.ui.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.utils.Logger;

import butterknife.ButterKnife;

/**
 * 类描述：fragment的基类
 * 创建人：Chris
 * 创建时间：2017/3/7 17:37
 */
public abstract class BaseFragment extends Fragment {

    protected String TAG = this.getClass().getSimpleName();

    protected Logger logger= Logger.getLogger();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        View rootView = inflater.inflate(provideContentViewId(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    public void initData() {

    }

    public void initListener() {

    }


    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();


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
