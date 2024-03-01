package com.example.practicaandroid.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaandroid.data.model.FacturaModel
import com.example.practicaandroid.domain.GetFacturasFiltradasUseCase
import com.example.practicaandroid.domain.GetFacturasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelFacturas @Inject constructor(
    private val getFacturasUseCase: GetFacturasUseCase,
    private val getFacturasFiltradasUseCase: GetFacturasFiltradasUseCase

) : ViewModel() {

    var facturas_ = MutableLiveData<List<FacturaModel>>()

    fun onCreate(retromockActivado: Boolean) {
        viewModelScope.launch {
            val facturas = getFacturasUseCase(retromockActivado)
            facturas.let {
                facturas_.postValue(it)
            }
        }
    }

    fun filtrarFacturas(
        estado: String,
        importe: Float,
        fechaSuperior: String,
        fechaInferior: String
    ) {
        viewModelScope.launch {
            val facturas = getFacturasFiltradasUseCase(
                estado = estado,
                importe = importe,
                fechaSuperior = fechaSuperior,
                fechaInferior = fechaInferior
            )
            facturas.let {
                facturas_.postValue(it)
            }
        }

    }
}