package com.example.practicaandroid.data

import com.example.practicaandroid.model.respuestaApiFacturas
import retrofit2.Response
import retrofit2.http.GET

interface FacturasApiClient {
    @GET("/facturas")
    suspend fun getFacturas(): Response<respuestaApiFacturas>
}