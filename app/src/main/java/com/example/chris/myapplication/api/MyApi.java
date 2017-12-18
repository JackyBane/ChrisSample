package com.example.chris.myapplication.api;


import com.example.chris.myapplication.api.model.response.BaseResponse;
import com.example.chris.myapplication.api.model.response.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @创建者 CSDN_LQR
 * @描述 server端api
 */

public interface MyApi {

    String BASE_URL = "http://api.sealtalk.im/";



//    //注册
//    @POST("user/register")
//    Observable<RegisterResponse> register(@Body RequestBody body);

    //登录
    @POST("user/login")
    Observable<BaseResponse<LoginResponse>> login(@Body RequestBody body);

//    //获取 token 前置条件需要登录   502 坏的网关 测试环境用户已达上限
//    @GET("user/get_token")
//    Observable<GetTokenResponse> getToken();



}
