package com.example.practicaandroid.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaandroid.domain.GetFacturasUseCase
import com.example.practicaandroid.model.FacturaModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelFacturas @Inject constructor(
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