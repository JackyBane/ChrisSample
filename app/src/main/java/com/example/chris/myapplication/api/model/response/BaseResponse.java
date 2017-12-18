package com.example.chris.myapplication.api.model.response;

import java.io.Serializable;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/18 18:36
 */

public class BaseResponse<T> implements Serializable {
    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public T data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
