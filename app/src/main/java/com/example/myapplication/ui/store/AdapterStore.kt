package com.pragma.kalash.ui.catalog


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.Store
import com.example.myapplication.ui.menu.Menus


class AdapterStore(
    val userList: ArrayList<com.example.myapplication.models.Store.StoreList>,
    context: Context?
) : RecyclerView.Adapter<AdapterStore.ViewHolder>() {

    val con = context

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterStore.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_store, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: AdapterStore.ViewHolder, position: Int) {
        holder.bindItems(userList[position])
        holder.name.setOnClickListener {

            val intent = Intent(con, Menus::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("APIKEY", userList[position].apikey)
            if (con != null) {
                con.startActivity(intent)
            }
        }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById(R.id.name) as TextView
        fun bindItems(store: Store.StoreList) {
            name.text = store.negocio



        }
    }
}