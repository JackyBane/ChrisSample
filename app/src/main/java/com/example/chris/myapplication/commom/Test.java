package com.example.chris.myapplication.commom;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/8 9:18
 */

public class Test {



    private String name;

    public Test() {
        name="chris is me";
    }


    public void setName( String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }






}
