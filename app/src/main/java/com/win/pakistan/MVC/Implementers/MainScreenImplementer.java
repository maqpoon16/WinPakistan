package com.win.pakistan.MVC.Implementers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.win.pakistan.MVC.Presentors.MainScreenPresenter;
import com.win.pakistan.MVC.Views.MainScreenView;
import com.win.pakistan.Models.LuckDrawModels.LuckyDrawModel;
import com.win.pakistan.Models.TimeSpan;
import com.win.pakistan.R;
import com.win.pakistan.Services.apiServices;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainScreenImplementer implements MainScreenPresenter {
    private final MainScreenView mainScreenView;
    private String EVENT_DATE_TIME = "2021-10-26 20:00:00";
    private final Handler handler = new Handler();
    private Runnable runnable;
    private Date event_date ,current_date;
    private SimpleDateFormat dateFormat;
    public MainScreenImplementer(MainScreenView mainScreenView,String luckyDrawTime) {
        this.mainScreenView = mainScreenView;
        if(luckyDrawTime!=null && !luckyDrawTime.isEmpty()){
            EVENT_DATE_TIME = luckyDrawTime;
        }
    }


    @Override
    public void TimerProcess(Activity context ) {

        runnable = new Runnable() {
            @SuppressLint({"DefaultLocale", "SimpleDateFormat"})
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    dateFormat = new SimpleDateFormat(context.getResources().getString(R.string.date_formate));
                    event_date = dateFormat.parse(EVENT_DATE_TIME);
                    current_date = new Date();
                    try{
                        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                        httpClient.readTimeout(30, TimeUnit.SECONDS);
                        httpClient.connectTimeout(30, TimeUnit.SECONDS);
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(context.getResources().getString(R.string.WorldTimeSpanUrl))
                                .addConverterFactory(GsonConverterFactory.create())
                                .client(httpClient.build()).build();
                        apiServices service = retrofit.create(apiServices.class);
                        Call<TimeSpan> call = service.getWorldTimePakistan();
                        call.enqueue(new Callback<TimeSpan>() {
                            @Override
                            public void onResponse(@NotNull Call<TimeSpan> call, @NotNull Response<TimeSpan> response) {
                                if(response.body() != null){
                                    TimeSpan timeSpan = response.body();
                                    try {
                                        String data = timeSpan.getDatetime().replace("T"," ").split("\\.")[0];
                                        current_date = dateFormat.parse(data);
                                        Log.d("Current Time : ",timeSpan.getDatetime());
                                        if (!current_date.after(event_date)) {
                                            long diff = event_date.getTime() - current_date.getTime();
                                            long Days = diff / (24 * 60 * 60 * 1000);
                                            long Hours = diff / (60 * 60 * 1000) % 24;
                                            long Minutes = diff / (60 * 1000) % 60;
                                            long Seconds = diff / 1000 % 60;
                                            mainScreenView.SetTimer(String.format("%02d", Days)+" Day\n"+String.format("%02d", Hours)+":"+String.format("%02d", Minutes)+":"+String.format("%02d", Seconds));
                                        } else {
                                            handler.removeCallbacks(runnable);
                                        }
                                    } catch (ParseException e) {
                                        mainScreenView.ShowException(e.getLocalizedMessage());
                                    }
                                }

                            }

                            @Override
                            public void onFailure(@NotNull Call<TimeSpan> call, @NotNull Throwable t) {
                                mainScreenView.ShowFailureMessage(t.getLocalizedMessage());

                            }
                        });
                    }catch (Exception e){
                        mainScreenView.ShowException(e.getLocalizedMessage());

                    }

                } catch (Exception e) {
                    mainScreenView.ShowException(e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    @Override
    public void GetLuckyDrawData(Activity context) {
        try{
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.readTimeout(60, TimeUnit.SECONDS);
            httpClient.connectTimeout(60, TimeUnit.SECONDS);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(context.getResources().getString(R.string.ServerBaseUrl))
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build()).build();
            apiServices service = retrofit.create(apiServices.class);
            Call<LuckyDrawModel> call = service.getLuckDraw();
            call.enqueue(new Callback<LuckyDrawModel>() {
                @Override
                public void onResponse(@NotNull Call<LuckyDrawModel> call, @NotNull Response<LuckyDrawModel> response) {
                    if(response.body() != null){
                        LuckyDrawModel luckyDrawModel = response.body();
                        mainScreenView.onlineLuckyDrawData(luckyDrawModel);
                        return;
                    }
                    Toast.makeText(context, "LuckyDrawData Failed!", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(@NotNull Call<LuckyDrawModel> call, @NotNull Throwable t) {
                    mainScreenView.ShowFailureMessage(t.getLocalizedMessage());

                }
            });
        }catch (Exception e){
            mainScreenView.ShowException(e.getLocalizedMessage());

        }

    }
}
