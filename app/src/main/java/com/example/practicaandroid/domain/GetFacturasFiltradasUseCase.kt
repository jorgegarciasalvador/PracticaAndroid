package com.example.practicaandroid.domain

import com.example.practicaandroid.data.FacturasRepository
import com.example.practicaandroid.model.FacturaModel
import com.example.practicaandroid.model.toDatabase
import java.text.SimpleDateFormat
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
        )

        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val fechaInferiorFormateada = formatter.parse(fechaInferior)
        val fechaSuperiorFormateada = formatter.parse(fechaSuperior)
        return if (facturas.isNotEmpty()) {
            var facturasFiltradas = mutableListOf<FacturaModel>()
            facturas.forEach {
                val fechaFormateada = formatter.parse(it.fecha)
                if((fechaFormateada.compareTo(fechaInferiorFormateada) < 0 ) && (fechaFormateada.compareTo(fechaSuperiorFormateada) > 0) ){
                    facturasFiltradas.add(it)
                }
            }

            repository.clearFacturas()
            repository.insertFacturas(facturasFiltradas.toList().map { it.toDatabase() })
            facturasFiltradas.toList()
        } else {
            emptyList()
        }
    }
}