package com.win.pakistan.Services;

import com.win.pakistan.Models.LuckDrawModel;
import com.win.pakistan.Models.TimeSpan;
import com.win.pakistan.Models.UserData;
import com.win.pakistan.Models.authResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface apiServices {
    //post command to get authenticate from server..

    @POST("signin")
    Call<authResponse> getLogin(@Body UserData userData);

     @POST("signup")
    Call<authResponse> getSignUp(@Body UserData userData);

     @POST("update-user")
    Call<authResponse> updateProfile(@Body UserData userData);

     @GET("get-lucky-draw/1")
    Call<LuckDrawModel> getLuckDraw();

     @GET("Karachi")
    Call<TimeSpan> getWorldTimePakistan();

     @GET("single-user/{userId}")
    Call<authResponse> getUser(@Path("userId") int id);

}
