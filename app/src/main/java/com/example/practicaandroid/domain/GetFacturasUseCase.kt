package com.example.practicaandroid.domain

import com.example.practicaandroid.data.FacturasRepository
import com.example.practicaandroid.model.FacturaModel
import com.example.practicaandroid.model.toDatabase
import javax.inject.Inject

class GetFacturasUseCase @Inject constructor(
    private val repository: FacturasRepository
) {
    suspend operator fun invoke(): List<FacturaModel> {
        val facturas = repository.getAllFacturasFromApi()
        return if (facturas.isNotEmpty()) {
            repository.clearFacturas()
            repository.insertFacturas(facturas.map { it -> it.toDatabase() })
            facturas
        } else {
            repository.getAllFacturasFromRoom()
        }
    }
}