package com.example.practicaandroid.domain

import com.example.practicaandroid.data.FacturasRepository
import com.example.practicaandroid.model.FacturaModel

class GetFacturasUseCase(private val repository: FacturasRepository) {
    suspend operator fun invoke():List<FacturaModel>{
        val facturas = repository.getAllFacturas()

        return facturas
    }
}