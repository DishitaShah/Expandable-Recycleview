package com.example.myapplication.ui.menu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.myapplication.R;
import com.example.myapplication.models.Menu;
import com.example.myapplication.models.MenuMap;
import com.example.myapplication.models.Store;
import com.example.myapplication.network.APIClient;
import com.example.myapplication.network.DataService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mindorks.placeholderview.ExpandablePlaceHolderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menus extends AppCompatActivity {
    Toolbar toolbar;
    DataService apiInterface;
    Bundle bundle;
    String apikey;
    List<MenuMap> listMenucategory = new ArrayList<>();
    ExpandablePlaceHolderView expandablePlaceHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuitems);
        toolbar = findViewById(R.id.toolbar);
        expandablePlaceHolder = findViewById(R.id.expandablePlaceHolder);
                toolbar.setTitle("Menu List");

                bundle = getIntent().getExtras();
                apikey = bundle.getString("APIKEY");

                getMenus(apikey);

    }

    private void getMenus(String apikey) {
        apiInterface = APIClient.getService(this);

      ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait ..");
        dialog.show();
        Call<Menu> call = apiInterface.getMenus(apikey);
        call.enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                dialog.dismiss();

                Log.d("TAG",response.code()+"");

                String displayResponse = "";

                Menu menu = response.body();

                List<Menu.MenuList> datumList = menu.getMenuList();
                HashMap<String, String> child_prices = new HashMap<>();
                Multimap<String, String> map = ArrayListMultimap.create();
                listMenucategory.clear();
                for (int i =0 ; i<datumList.size(); i++) {
                     Menu.CategroyList datumcatList = datumList.get(i).getCategoryList();
                    // header_menus.add(datumcatList.getNombremenu());
                    child_prices.put(datumList.get(i).getNombre(), datumList.get(i).getPrecioSugerido());
                    map.put(datumcatList.getNombremenu(), datumList.get(i).getNombre());

                }
                Set<String> keys = map.keySet();
                for (String key : keys) {
                    System.out.println("Key = " + key);
                    expandablePlaceHolder.addView(new HeaderView(Menus.this, key));
                    System.out.println("Values = " + map.get(key) + "n");

                    for (String menucat : map.get(key))
                        {
                            expandablePlaceHolder.addView(new ChildView(Menus.this, menucat, child_prices,Menus.this));
                        }

                }

            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                dialog.dismiss();
            }


        });
    }


}
