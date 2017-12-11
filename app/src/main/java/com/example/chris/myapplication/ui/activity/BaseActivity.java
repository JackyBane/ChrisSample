package com.example.chris.myapplication.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.example.chris.myapplication.commom.Test1;
import com.example.chris.myapplication.dragger2.component.ActivityComponent;
import com.example.chris.myapplication.dragger2.component.DaggerActivityComponent;
import com.example.chris.myapplication.dragger2.module.ActivityModule;
import com.example.mylibrary.utils.Logger;
import com.example.mylibrary.utils.StatusBarUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;


/**
 * 类描述： activity的基类
 * 创建人：Chris
 * 创建时间：2017/3/7 17:25
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = this.getClass().getSimpleName();
    private ActivityComponent activityComponent;

    protected Logger logger= Logger.getLogger();

    @Inject
    Test1 test1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //定义全屏参数
//        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        //获得当前窗体对象
//        Window window = this.getWindow();
//        //设置当前窗体为全屏显示
//        window.setFlags(flag, flag);


        activityComponent = DaggerActivityComponent
                .builder()
                .activityModule(new ActivityModule(BaseActivity.this))
                .build();
        activityComponent.inj(this);

        // 初始化ui
        setContentView(initUI());
        StatusBarUtil.setColor(this, Color.RED);

        ButterKnife.bind(this);

        test1.say();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    public void onAttachedToWindow() {
        initData();
        // 事件监听
        initListener();
    }

    /**
     * 初始化ui
     **/
    @LayoutRes
    protected abstract int initUI();

    /**
     * 初始化数据
     **/
    protected abstract void initData();

    /**
     * 初始化监听
     **/
    protected abstract void initListener();


    /**********************
     * activity跳转
     **********************************/
    public void openActivity(Class<?> targetActivityClass) {
        openActivity(targetActivityClass, null);
    }

    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void openActivityAndCloseThis(Class<?> targetActivityClass) {
        openActivity(targetActivityClass);
        this.finish();
    }

    public void openActivityAndCloseThis(Class<?> targetActivityClass, Bundle bundle) {
        openActivity(targetActivityClass, bundle);
        this.finish();
    }

    /***************************************************************/

    // 点击空白区域 自动隐藏软键盘
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    /**
     * 收起键盘
     */
    public void closeInputMethod() {
        View view = getWindow().peekDecorView();// 用于判断虚拟软键盘是否是显示的
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /*
     * ************Fragment相关方法************************************************
     *
     */
    protected Fragment currentFragment;

    /**
     * Fragment替换(当前destroy,新的create)
     */
    public void fragmentReplace(@IdRes int target, Fragment toFragment, boolean backStack) {
        FragmentManager manager = getSupportFragmentManager();
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

    /**
     * Fragment替换(核心为隐藏当前的,显示现在的,用过的将不会destroy与create)
     */
    public void smartFragmentReplace(int target, Fragment toFragment) {
        FragmentManager manager = getSupportFragmentManager();
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
        try {
            transaction.commit();
            // toFragment更新为当前的
            currentFragment = toFragment;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
