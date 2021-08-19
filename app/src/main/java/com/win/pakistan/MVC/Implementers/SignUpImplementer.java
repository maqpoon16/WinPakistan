package com.win.pakistan.MVC.Implementers;

import android.app.Activity;
import android.text.TextUtils;

import com.win.pakistan.MVC.Presentors.LoginPresenter;
import com.win.pakistan.MVC.Presentors.SignUpPresenter;
import com.win.pakistan.MVC.Views.SignUpView;
import com.win.pakistan.Models.UserAuth;
import com.win.pakistan.Models.UserData;
import com.win.pakistan.Models.UserSignup;
import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;
import com.win.pakistan.Services.apiServices;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.win.pakistan.Common.FinalStrings.baseUrlLogin;
import static com.win.pakistan.Common.FinalStrings.snackbarValidationErrorPass;
import static com.win.pakistan.Common.FinalStrings.snackbarValidationErrorUser;

public class SignUpImplementer implements SignUpPresenter {
    private final SignUpView signUpView;
    private final Activity context;

    public SignUpImplementer(SignUpView signUpView, Activity context) {
        this.signUpView = signUpView;
        this.context = context;
    }
    @Override
    public void SignUpProcess(UserSignup data) {
        try{
            if (data == null){
                signUpView.ShowValidationError(context.getResources().getString(R.string.snackbarSignupModelValidation));
                return;
            }
            if (TextUtils.isEmpty(data.getFullName())){

                signUpView.ShowValidationError(context.getResources().getString(R.string.snackbarSignupModelValidationFullName));
                return;
            } if (TextUtils.isEmpty(data.getUsername())){

                signUpView.ShowValidationError(context.getResources().getString(R.string.snackbarSignupModelValidationUser));
                return;
            } if (TextUtils.isEmpty(data.getPassword())){

                signUpView.ShowValidationError(context.getResources().getString(R.string.snackbarSignupModelValidationPass));
                return;
            } if (TextUtils.isEmpty(data.getMobileNumber())){

                signUpView.ShowValidationError(context.getResources().getString(R.string.snackbarSignupModelValidationContact));
                return;
            } if (TextUtils.isEmpty(data.getGender())){

                signUpView.ShowValidationError(context.getResources().getString(R.string.snackbarSignupModelValidationGender));
                return;
            } if (TextUtils.isEmpty(data.getAge())){

                signUpView.ShowValidationError(context.getResources().getString(R.string.snackbarSignupModelValidationAge));
                return;
            }

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.readTimeout(6, TimeUnit.SECONDS);
            httpClient.connectTimeout(6, TimeUnit.SECONDS);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrlLogin)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()).build();
            apiServices service = retrofit.create(apiServices.class);
            Call<authResponse> call = service.getSignUp(data);
            call.enqueue(new Callback<authResponse>() {
                @Override
                public void onResponse(@NotNull Call<authResponse> call, @NotNull Response<authResponse> response) {
                    if(response!=null && response.body()!=null){
                        authResponse authResponse = response.body();

                        if(authResponse.isStatus()){
                            signUpView.OnSignUpSuccessfull();
                        }else {
                            signUpView.OnSignUpFailed(authResponse.getMessage());
                        }
                    }

                }

                @Override
                public void onFailure(@NotNull Call<authResponse> call, @NotNull Throwable t) {
                    signUpView.OnSignUpFailed(t.getLocalizedMessage());

                }
            });
        }catch (Exception e){
            signUpView.ShowException(e.getLocalizedMessage());

        }
    }
}
