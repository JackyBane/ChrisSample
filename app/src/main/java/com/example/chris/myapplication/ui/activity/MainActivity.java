package com.example.chris.myapplication.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.chris.myapplication.R;
import com.example.chris.myapplication.api.model.CardRe;
import com.example.chris.myapplication.presenter.activity.MainAcitivityPresenter;
import com.example.chris.myapplication.ui.base.BaseActivity;
import com.example.chris.myapplication.utils.ToastUtils;
import com.example.mylibrary.utils.ActivityUtils;

import butterknife.BindView;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/7 17:22
 */

public class MainActivity extends BaseActivity {


    private MainAcitivityPresenter mainActivityPresenter = new MainAcitivityPresenter(this);

    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mainActivityPresenter.getData("024405d8599bbb32f0ab207983ac559b");
    }

    @Override
    public void success(Object o) {
        tvContent.setText(((CardRe) o)._$2);
    }

    public void show() {
        ToastUtils.showToast("---------------------------------------------");
    }

    /*****************
     * 双击退出程序
     ************************************************/
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastUtils.showToast("再按一次退出程序");
                // 将系统当前的时间赋值给exitTime
                exitTime = System.currentTimeMillis();
            } else {
                ActivityUtils.finishAllActivities();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
