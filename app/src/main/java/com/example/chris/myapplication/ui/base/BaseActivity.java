package com.example.chris.myapplication.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.chris.myapplication.R;
import com.example.chris.myapplication.ui.IView;
import com.example.chris.myapplication.utils.ToastUtils;
import com.example.mylibrary.utils.Logger;

import butterknife.ButterKnife;


/**
 * 类描述： activity的基类
 * 创建人：Chris
 * 创建时间：2017/3/7 17:25
 */
public abstract class BaseActivity extends AppCompatActivity implements IView{

    protected String TAG = this.getClass().getSimpleName();

    protected Logger logger = Logger.getLogger();


//    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        ButterKnife.bind(this);

//        initToolbar();

        //沉浸式状态栏
//        StatusBarUtil.setColor(this, UIUtils.getColor(R.color.colorPrimaryDark), 10);

        initView();
        initData();
        initListener();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
    }

    protected void setToolbarVisible(int visibility) {
        toolbar.setVisibility(visibility);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }



    //在setContentView()调用之前调用，可以设置WindowFeature(如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
    public void init() {

    }

    public void initView() {
    }

    public void initData() {
    }

    public void initListener() {
    }

    @Override
    public void success(Object o) {

    }

    @Override
    public void failed(String msg) {
        ToastUtils.showToast(msg);
    }



    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    /**
     * 是否让Toolbar有返回按钮(默认可以，一般一个应用中除了主界面，其他界面都是可以有返回按钮的)
     */
    protected boolean isToolbarCanBack() {
        return true;
    }

    /*------------------ toolbar的一些视图操作 ------------------*/
    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    public void setToolbarNavigationIcon(int iconID) {
        // Navigation Icon 要設定在 setSupoortActionBar 才有作用
        // 否則會出現 back button
        toolbar.setNavigationIcon(iconID);
    }


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
