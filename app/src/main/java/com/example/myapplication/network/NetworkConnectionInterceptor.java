package com.example.myapplication.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkConnectionInterceptor implements Interceptor {
 
    private Context mContext;
 
    public NetworkConnectionInterceptor(Context context) {
        mContext = context;
    }
 
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        if (!isConnected()) {

                throw new NoConnectivityException();

            // Throwing our custom exception 'NoConnectivityException'
        }
 
       /* Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
      */

        Request originalRequest = chain.request();
        if (!"index.php?r=menu".contains(originalRequest.url().toString()) ) {
            return chain.proceed(originalRequest);
        }

        String token = originalRequest.header("APIKEY");

        assert token != null;
        Request newRequest = originalRequest.newBuilder()
                .header("X-Authorization", token)
                .build();

        return chain.proceed(newRequest);
    }
 
   public boolean isConnected(){
      ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
       assert connectivityManager != null;
       NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
      return (netInfo != null && netInfo.isConnected());
   }
 
}