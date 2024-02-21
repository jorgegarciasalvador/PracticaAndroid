package com.example.practicaandroid.data

import com.example.practicaandroid.model.FacturaModel

class FacturasRepository(private val apiClient: FacturasApiClient) {
    suspend fun getAllFacturas():List<FacturaModel>{
        return apiClient.getFacturas()
    }
}