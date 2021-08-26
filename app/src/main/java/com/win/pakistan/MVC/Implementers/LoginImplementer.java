package com.win.pakistan.MVC.Implementers;

import android.app.Activity;
import android.text.TextUtils;

import com.win.pakistan.MVC.Presentors.LoginPresenter;
import com.win.pakistan.MVC.Views.LoginView;
import com.win.pakistan.Models.UserAuth;
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
import static com.win.pakistan.Common.Methods.setAutoLogin;

public class LoginImplementer  implements LoginPresenter {
    private final LoginView loginView;

    public LoginImplementer(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void LoginProcess(String user, String password , Activity context) {
      try{
          if (TextUtils.isEmpty(user)){

              loginView.ShowValidationError(context.getResources().getString(R.string.snackbarValidationErrorUser));
              return;
          }
          if (TextUtils.isEmpty(password)){

              loginView.ShowValidationError(context.getResources().getString(R.string.snackbarValidationErrorPass));
              return;
          }
          UserAuth userAuth = new UserAuth();
          userAuth.setMobileNumber(user);
          userAuth.setPassword(password);

          OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
          httpClient.readTimeout(6, TimeUnit.SECONDS);
          httpClient.connectTimeout(6, TimeUnit.SECONDS);
          Retrofit retrofit = new Retrofit.Builder()
                  .baseUrl(context.getResources().getString(R.string.ServerBaseUrl))
                  .addConverterFactory(GsonConverterFactory.create())
                  .client(httpClient.build()).build();
          apiServices service = retrofit.create(apiServices.class);
          Call<authResponse> call = service.getLogin(userAuth);
          call.enqueue(new Callback<authResponse>() {
              @Override
              public void onResponse(@NotNull Call<authResponse> call, @NotNull Response<authResponse> response) {
                  if(response.body() != null){
                      authResponse authResponse = response.body();

                      if(authResponse.isStatus()){
                          setAutoLogin(context,authResponse);
                          loginView.OnloginSuccessfull();
                      }else {
                          loginView.OnloginFailed(authResponse.getMessage());
                      }
                  }

              }

              @Override
              public void onFailure(@NotNull Call<authResponse> call, @NotNull Throwable t) {
                  loginView.OnloginFailed(t.getLocalizedMessage());

              }
          });
      }catch (Exception e){
          loginView.ShowException(e.getLocalizedMessage());

      }

    }
}
