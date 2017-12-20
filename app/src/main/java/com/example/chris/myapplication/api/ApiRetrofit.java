package com.example.chris.myapplication.api;

import com.example.chris.myapplication.api.base.BaseApiRetrofit;
import com.example.chris.myapplication.api.model.response.BaseResponse;
import com.example.chris.myapplication.api.model.response.CardRe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * @描述 使用Retrofit对网络请求进行配置
 */
public class ApiRetrofit extends BaseApiRetrofit {

    public MyApi mApi;
    public static ApiRetrofit mInstance;

    private ApiRetrofit() {
        super();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //在构造方法中完成对Retrofit接口的初始化
        mApi = new Retrofit.Builder()
                .baseUrl(MyApi.BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(MyApi.class);
    }

    public static ApiRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (ApiRetrofit.class) {
                if (mInstance == null) {
                    mInstance = new ApiRetrofit();
                }
            }
        }
        return mInstance;
    }

    private RequestBody getRequestBody(Object obj) {
        String route = new Gson().toJson(obj);
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);
        return body;
    }



    //================================接口配置============================================================


//    //登录
//    public Observable<BaseResponse<LoginResponse>> login(String phone, String password) {
//        return mApi.login(getRequestBody(new LoginRequest(phone, password)));
//    }

    public Observable<BaseResponse<CardRe>> getCard(String key) {
        return mApi.getCard(key);
    }



}
