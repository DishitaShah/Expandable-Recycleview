package com.example.myapplication.ui.store

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Store
import com.example.myapplication.network.APIClient
import com.example.myapplication.network.NoConnectivityException
import com.pragma.kalash.ui.catalog.AdapterStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Stores : AppCompatActivity() {


var arraylist: ArrayList<Store.StoreList> = ArrayList()
    lateinit var adapter : AdapterStore
    lateinit var toolbar : Toolbar
    lateinit var rv_store : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.store_activity)


         rv_store = findViewById<RecyclerView>(R.id.rv_store)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle("Store List")
        getStore()

    }

    private fun getStore() {
       val progressDialog= ProgressDialog(this)
        progressDialog.setMessage("Please wait...")
        progressDialog.setCancelable(true)
        progressDialog.setCanceledOnTouchOutside(true)
        progressDialog.show()
        val apiInterface = APIClient.getService(this)
        val call3 = apiInterface.getStore()

        call3.enqueue(object : Callback<Store> {
            override fun onResponse(call: Call<Store>, response: Response<Store>) {
                val storeList = response.body()
                val datumList: List<Store.StoreList> = storeList?.storeList!!
                for (datum in datumList) {

                    arraylist.add(datum)
                }

                    adapter = AdapterStore(arraylist,applicationContext)
                    val layoutManager: RecyclerView.LayoutManager =
                        LinearLayoutManager(applicationContext)
                    rv_store.setLayoutManager(layoutManager)
                    rv_store.setAdapter(adapter)

                    progressDialog.hide()

            }

            override fun onFailure(call: Call<Store>, t: Throwable) {
                call.cancel()
                progressDialog.hide()

                if(t is NoConnectivityException) {

                    Toast.makeText(applicationContext,t.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}