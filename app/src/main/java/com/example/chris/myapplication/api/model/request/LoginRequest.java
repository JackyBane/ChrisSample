package com.example.chris.myapplication.api.model.request;

/**
 * 类描述：
 * 创建人 Chris
 * 创建时间：2017/12/18 18:46
 */

public class LoginRequest {
    public String userName;
    public String password;

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
