package com.example.practicaandroid.data

import com.example.practicaandroid.data.dao.FacturasDao
import com.example.practicaandroid.model.DetallesFacturaModel
import com.example.practicaandroid.model.FacturaEntity
import com.example.practicaandroid.model.FacturaModel
import com.example.practicaandroid.model.toDomain
import javax.inject.Inject

class FacturasRepository @Inject constructor(
    private val facturasDao: FacturasDao,
    private val apiService: FacturasService
) {
    suspend fun getAllFacturasFromApi(): List<FacturaModel> {
        return apiService.getFacturas()
    }
    suspend fun getAllFacturasFromRetromock():List<FacturaModel>{
        return apiService.getMockedFacturas()
    }

    suspend fun getAllFacturasFromRoom(): List<FacturaModel> {
        return facturasDao.getAllFacturas().map { factura -> factura.toDomain() }
    }

    suspend fun insertFacturas(facturas: List<FacturaEntity>) {
        facturasDao.insertAll(facturas)
    }

    suspend fun clearFacturas() {
        facturasDao.deleteAllFacturas()
    }

    suspend fun getFacturasFiltradas(
        estado: String,
        importe: Float
    ): List<FacturaModel> {
        return if(estado != "") {
            facturasDao.getFacturasFiltradas(estado, importe)
                .map { it.toDomain() }
        }else{
            facturasDao.getFacturasFiltradas(importe).map { it.toDomain() }
        }
    }

    suspend fun getDetallesFacturas():DetallesFacturaModel{
        return apiService.getMockedDetallesFacturas()
    }
}