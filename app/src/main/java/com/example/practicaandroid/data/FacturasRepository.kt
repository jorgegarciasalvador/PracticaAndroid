package com.example.practicaandroid.data

import com.example.practicaandroid.model.FacturaModel

class FacturasRepository {
    private val apiService = FacturasService()
    suspend fun getAllFacturas(): List<FacturaModel> {
        return apiService.getFacturas()
    }
}