package com.example.chris.myapplication.api.model.response;

import java.io.Serializable;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/18 18:36
 */

public class BaseResponse<T> implements Serializable {
    @SerializedName("code")
    public int error_code;
    @SerializedName("msg")
    public String reason;
    @SerializedName("data")
    public T result;

    public int getCode() {
        return error_code;
    }

    public String getMsg() {
        return reason;
    }

    public T getData() {
        return result;
    }
}
