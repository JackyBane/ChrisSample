package com.example.chris.myapplication.api;


import com.example.chris.myapplication.api.model.response.BaseResponse;
import com.example.chris.myapplication.api.model.response.CardRe;
import com.example.chris.myapplication.api.model.response.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @创建者 CSDN_LQR
 * @描述 server端api
 */

public interface MyApi {

    String BASE_URL = "http://v.juhe.cn/";


    //登录
//    @POST("user/login")
//    Observable<BaseResponse<LoginResponse>> login(@Body RequestBody body);

    @GET("certificates/typeList.php")
    Observable<BaseResponse<CardRe>> getCard(@Query("key") String key);



}
