package com.win.pakistan.MVC.Implementers;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.widget.DatePicker;

import com.win.pakistan.MVC.Presentors.AccountInfoScreenPresenter;
import com.win.pakistan.MVC.Views.AccountInfoScreenView;
import com.win.pakistan.Models.UserAuth;
import com.win.pakistan.Models.authResponse;
import com.win.pakistan.R;
import com.win.pakistan.Services.apiServices;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.win.pakistan.Common.Methods.setAutoLogin;

public class AccountInfoScreenImplementer implements AccountInfoScreenPresenter {
    private final AccountInfoScreenView accountInfoScreenView;

    public AccountInfoScreenImplementer(AccountInfoScreenView accountInfoScreenView) {
        this.accountInfoScreenView = accountInfoScreenView;
    }

    @Override
    public void openCalenderForDOB(Activity context , String dob) {
        int year = Integer.parseInt(dob.split("-")[0]);
        int month = Integer.parseInt(dob.split("-")[1]);
        int day = Integer.parseInt(dob.split("-")[2]);
        DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker arg0,
                                  int arg1, int arg2, int arg3) {
                accountInfoScreenView.SetDOB(arg3 + "/" + (arg2 + 1) + "/" + arg1);
                accountInfoScreenView.SetAge(getAge(arg1, arg2 + 1, arg3));
            }
        };
        new DatePickerDialog(context, myDateListener, year, month, day).show();
    }

    @Override
    public void getOnlineProfile(Activity context,int id ) {
        try{

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.readTimeout(6, TimeUnit.SECONDS);
            httpClient.connectTimeout(6, TimeUnit.SECONDS);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(context.getResources().getString(R.string.ServerBaseUrl))
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()).build();
            apiServices service = retrofit.create(apiServices.class);
            Call<authResponse> call = service.getUser(id);
            call.enqueue(new Callback<authResponse>() {
                @Override
                public void onResponse(@NotNull Call<authResponse> call, @NotNull Response<authResponse> response) {
                    if(response.body() != null){
                        authResponse authResponse = response.body();
                        if(authResponse.isStatus()){
                            accountInfoScreenView.SetOnlineProfile(authResponse.getData());
                        }else {
                            accountInfoScreenView.ShowFailureMessage(authResponse.getMessage());
                        }
                    }

                }

                @Override
                public void onFailure(@NotNull Call<authResponse> call, @NotNull Throwable t) {
                    accountInfoScreenView.ShowFailureMessage(t.getLocalizedMessage());

                }
            });
        }catch (Exception e){
            accountInfoScreenView.ShowException(e.getLocalizedMessage());

        }
    }

    private String getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return Integer.toString(age);
    }
}
