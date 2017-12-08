package com.example.chris.myapplication.commom;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/8 11:16
 */

public class Test1 {
    private  Activity activity;
    private String name;

    public Test1(Activity activity) {
        this.activity=activity;
        name="this is test_1";
    }

    public String getName() {
        return name;
    }

    public void say() {
        Log.e("dsfdfdsf", "say: "+activity.toString() );
        Toast.makeText(activity, "test_1", Toast.LENGTH_SHORT).show();
    }
}
