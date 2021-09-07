package com.example.myapplication.others;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.myapplication.ProductActivity;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.Token;
import com.example.myapplication.service.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.myapplication.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.myapplication.service.ServiceAPI.BASE_Service;

public class DataToken {

    private final Context context;


    public DataToken(Context context) {
        this.context = context;
    }

    public void saveToken(String token) {

        SharedPreferences settings = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token", "Bearer " +  token);
        editor.putLong("expires", System.currentTimeMillis() + 86400000); //24 * 3600000
        editor.apply();
    }

    public String getToken() {
        SharedPreferences settings = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        long expires = settings.getLong("expires", 0);
        if (expires < System.currentTimeMillis()) {
            return "";
        }
        return settings.getString("token", "");
    }
}
