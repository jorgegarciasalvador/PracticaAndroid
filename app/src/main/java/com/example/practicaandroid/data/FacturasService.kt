package com.example.practicaandroid.data

import co.infinum.retromock.Retromock
import com.example.practicaandroid.model.FacturaModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class FacturasService @Inject constructor(
    private val retrofit: Retrofit,
    private val retromock: Retromock
) {
    suspend fun getFacturas(): List<FacturaModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(FacturasApiClient::class.java).getFacturas()
            response.body()?.facturas ?: emptyList()
        }
    }

    suspend fun getMockedFacturas():List<FacturaModel>{
        return withContext(Dispatchers.IO) {
            val response = retromock.create(FacturasApiClient::class.java).getFacturas()
            response.body()?.facturas ?: emptyList()
        }
    }
}