package com.example.practicaandroid.domain

import com.example.practicaandroid.data.FacturasRepository
import com.example.practicaandroid.data.model.FacturaModel
import com.example.practicaandroid.data.model.toDatabase
import javax.inject.Inject

class GetFacturasUseCase @Inject constructor(
    private val repository: FacturasRepository
) {
    suspend operator fun invoke(retromockActivado: Boolean): List<FacturaModel> {
        val facturas = repository.getAllFacturasFromApi()
        val mockedFacturas = repository.getAllFacturasFromRetromock()
        return if (mockedFacturas.isNotEmpty() && retromockActivado) {
            repository.clearFacturas()
            repository.insertFacturas(mockedFacturas.map { it.toDatabase() })
            mockedFacturas
        } else if (facturas.isNotEmpty() && !retromockActivado) {
            repository.clearFacturas()
            repository.insertFacturas(facturas.map { it.toDatabase() })
            facturas
        } else {
            return repository.getAllFacturasFromRoom()
        }
    }
}