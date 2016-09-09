package com.example.linearalgebra.avatarapp.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.linearalgebra.avatarapp.model.Model;
import com.example.linearalgebra.avatarapp.model.Person;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by LinearAlgebra on 09/09/2016.
 */
public class Network {
    private final Api api;
    private static final String BASE_URL = "https://raw.githubusercontent.com";

    public Network() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.i("Bert", "OkHttp: " + message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public Observable<ArrayList<Person>> fetchPeople() {
        return api.fetchPeople().map(Model::getPeople);
    }

    public static boolean isAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
