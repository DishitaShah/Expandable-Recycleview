package com.example.myapplication.network;



import com.example.myapplication.models.Menu;
import com.example.myapplication.models.Store;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface DataService {
    @Headers({

            "APIKEY: bd_suvlascentralpos"
    })
    @GET("index.php?r=configuraciones/franquicias")
    Call<Store> getStore();


      @GET("index.php?r=menu")
    Call<Menu> getMenus(@Header("APIKEY") String key);
}
