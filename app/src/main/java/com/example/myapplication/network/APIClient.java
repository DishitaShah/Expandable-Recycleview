package com.example.myapplication.network;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
  private static Retrofit retrofit;
  private static final String BASE_URL = "https://api.invupos.com/invuApiPos/";

  public static DataService getService(Context mContext) {
    if (retrofit == null) {
      NetworkConnectionInterceptor logging = new NetworkConnectionInterceptor(mContext);

      OkHttpClient.Builder oktHttpClient = new OkHttpClient.Builder()
              .addInterceptor(new NetworkConnectionInterceptor(mContext));

      retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .client(oktHttpClient.build()).build();
    }

    return retrofit.create(DataService.class);
  }
}
