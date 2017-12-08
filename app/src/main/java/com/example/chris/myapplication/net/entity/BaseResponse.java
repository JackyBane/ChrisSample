package com.example.chris.myapplication.net.entity;

import com.example.chris.myapplication.net.SerializedName;

import java.io.Serializable;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/8 16:32
 */

public class BaseResponse<T> implements Serializable {
    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public T data;
}
