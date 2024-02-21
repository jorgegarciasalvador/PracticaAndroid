package com.example.practicaandroid.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaandroid.domain.GetFacturasUseCase
import com.example.practicaandroid.model.FacturaModel
import kotlinx.coroutines.launch

class ViewModelFacturas(
    private val getFacturasUseCase: GetFacturasUseCase
) : ViewModel() {

    val facturas = MutableLiveData<List<FacturaModel>>()

    fun onCreate() {
        viewModelScope.launch {
            val factura = getFacturasUseCase()
            factura.let {
                facturas.postValue(it)
            }
        }
    }
}