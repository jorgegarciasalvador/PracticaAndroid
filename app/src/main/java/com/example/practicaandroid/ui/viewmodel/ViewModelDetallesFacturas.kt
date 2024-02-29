package com.example.practicaandroid.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaandroid.domain.GetDetallesFacturasUseCase
import com.example.practicaandroid.model.DetallesFacturaModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelDetallesFacturas @Inject constructor(
    private val getDetallesFacturasUseCase: GetDetallesFacturasUseCase
) : ViewModel() {

    val detallesFacturas_ = MutableLiveData<DetallesFacturaModel>()

    fun onCreate(){
        viewModelScope.launch {
            val detallesFacturas = getDetallesFacturasUseCase()
            detallesFacturas.let{
                detallesFacturas_.postValue(it)
            }
        }
    }
}