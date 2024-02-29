package com.example.practicaandroid.domain

import com.example.practicaandroid.data.FacturasRepository
import com.example.practicaandroid.model.FacturaModel
import com.example.practicaandroid.model.toDatabase
import javax.inject.Inject

class GetFacturasUseCase @Inject constructor(
    private val repository: FacturasRepository
) {
    suspend operator fun invoke(retromockActivado: Boolean): List<FacturaModel> {
        val facturas = repository.getAllFacturasFromApi()
        val mockedFacturas = repository.getAllFacturasFromRetromock()
        if (mockedFacturas.isNotEmpty() && retromockActivado) {
            repository.clearFacturas()
            repository.insertFacturas(mockedFacturas.map { it -> it.toDatabase() })
            return mockedFacturas
        } else if (facturas.isNotEmpty() && !retromockActivado) {
            repository.clearFacturas()
            repository.insertFacturas(facturas.map { it -> it.toDatabase() })
            return facturas
        } else {
            return repository.getAllFacturasFromRoom()
        }
    }
}