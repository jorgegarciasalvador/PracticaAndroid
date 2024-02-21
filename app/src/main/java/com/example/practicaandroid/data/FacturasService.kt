package com.example.practicaandroid.data

import com.example.practicaandroid.core.RetrofitHelper
import com.example.practicaandroid.model.FacturaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FacturasService() {

    private val retrofit = RetrofitHelper.getReftrofit()
    suspend fun getFacturas(): List<FacturaModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(FacturasApiClient::class.java).getFacturas()
            response.body() ?: emptyList()
        }
    }
}