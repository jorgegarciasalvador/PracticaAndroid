package com.example.practicaandroid.model

import com.google.gson.annotations.SerializedName

data class FacturaModel(
    @SerializedName("fecha")val fecha:String,
    @SerializedName("descEstado")val estado:String,
    @SerializedName("importeOrdenacion")val importe:Double
)
