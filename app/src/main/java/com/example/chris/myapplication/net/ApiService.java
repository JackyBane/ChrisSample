package com.example.chris.myapplication.net;

public interface ApiService {

    /**
     * 使用普通的retrofit方式获取数据
     * @return
     */
//    @GET("ezSQL/get_user.php")
//    Call<BaseResponse<List<UserModel>>> getUsers();


    /**
     * 使用rx+retrofit的方式获取数据
     */
//    @GET("ezSQL/get_user.php")
//    Observable<BaseResponse<List<UserModel>>> getUsersByRx();


//    @GET("api/cook/list")
//    Observable<TngouResponse<List<Cook>>> getCookList(@Query("page") int page, @Query("rows") int rows);
}
