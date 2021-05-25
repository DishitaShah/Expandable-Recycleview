package com.example.myapplication.models

import com.google.gson.annotations.SerializedName

class Store {
    @SerializedName("franquicias")
    val storeList: List<StoreList>? = null

    class StoreList(
        @field:SerializedName("APIKEY") var apikey: String,
        @field:SerializedName("tokenInvu") var tokenInvu: String,
        @field:SerializedName("negocio") var negocio: String,
        @field:SerializedName("principal") var principal: Boolean,
        @field:SerializedName("id_franquicia") var id_franquicia: String,
        @field:SerializedName("franquicia") var franquicia: String,
        @field:SerializedName("horaCierreLocal") var horaCierreLocal: String

    )
}

