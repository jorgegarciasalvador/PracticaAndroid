package com.example.practicaandroid.data

import com.example.practicaandroid.model.FacturaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FacturasService(private val apiClient: FacturasApiClient) {

    suspend fun getFacturas():List<FacturaModel>{
        return withContext(Dispatchers.IO){
            val response = apiClient.getFacturas()
            response
        }
    }
}