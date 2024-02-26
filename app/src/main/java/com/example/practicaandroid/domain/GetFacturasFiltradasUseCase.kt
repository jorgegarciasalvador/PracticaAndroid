package com.example.practicaandroid.domain

import com.example.practicaandroid.data.FacturasRepository
import com.example.practicaandroid.model.FacturaModel
import com.example.practicaandroid.model.toDatabase
import javax.inject.Inject

class GetFacturasFiltradasUseCase @Inject constructor(
    private val repository: FacturasRepository,

    ) {

    suspend operator fun invoke(
        estado: String,
        importe: Float,
        fechaSuperior: String,
        fechaInferior: String
    ): List<FacturaModel> {
        val facturas = repository.getFacturasFiltradas(
            estado = estado,
            importe = importe,
            fechaInferior = fechaInferior,
            fechaSuperior = fechaSuperior
        )

        return if (facturas.isNotEmpty()) {
            repository.clearFacturas()
            repository.insertFacturas(facturas.map { it.toDatabase() })
            facturas
        } else {
            emptyList()
        }
    }
}