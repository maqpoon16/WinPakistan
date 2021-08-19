package com.win.pakistan.Services;

import com.win.pakistan.Models.UserAuth;
import com.win.pakistan.Models.UserData;
import com.win.pakistan.Models.UserSignup;
import com.win.pakistan.Models.authResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface apiServices {
    //post command to get authenticate from server..

    @POST("signin")
    Call<authResponse> getLogin(@Body UserAuth userData);

     @POST("signup")
    Call<authResponse> getSignUp(@Body UserSignup userData);

}
