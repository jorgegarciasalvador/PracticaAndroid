package com.example.practicaandroid.domain

import com.example.practicaandroid.data.FacturasRepository
import com.example.practicaandroid.model.DetallesFacturaModel
import javax.inject.Inject

class GetDetallesFacturasUseCase @Inject constructor(
    private val repository: FacturasRepository
){

    suspend operator fun invoke(): DetallesFacturaModel {
        return repository.getDetallesFacturas()
    }
}