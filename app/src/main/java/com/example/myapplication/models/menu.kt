package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

class Menu {
    @SerializedName("data")
    val menuList: List<MenuList>? = null



    class MenuList(
        @field:SerializedName("APIKEY") var apikey: String,
        @field:SerializedName("precioSugerido") var precioSugerido: String,
        @field:SerializedName("nombre") var nombre: String,
        @SerializedName("categoria")
        val categoryList: CategroyList

    )
    class CategroyList (

        @field:SerializedName("idcategoriamenu") var idcategoriamenu: String,
        @field:SerializedName("nombremenu") var nombremenu: String,
        @field:SerializedName("porcentaje") var porcentaje: String
          )

}
