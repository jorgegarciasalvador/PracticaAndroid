package com.example.practicaandroid.ui.view.filtros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practicaandroid.R
import com.example.practicaandroid.databinding.ActivityFiltrosBinding
import com.google.android.material.slider.Slider

class FiltrosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFiltrosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiltrosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sliderImporte.addOnSliderTouchListener(object : Slider.OnSliderTouchListener{
            override fun onStartTrackingTouch(slider: Slider) {
                binding.tvImporteSlider.text = binding.sliderImporte.value.toInt().toString()
            }

            override fun onStopTrackingTouch(slider: Slider) {
                binding.tvImporteSlider.text = binding.sliderImporte.value.toInt().toString()
            }
        })
        binding.btnEliminarFiltros.setOnClickListener {
            clearFilters()
        }
    }

    private fun clearFilters(){
        binding.etFechaDesde.setText("dia/mes/año")
        binding.etFechaHasta.setText("dia/mes/año")
        binding.sliderImporte.value = 100F
        binding.tvImporteSlider.text = "100"
        binding.cbAnuladas.isChecked = false
        binding.cbCuotafija.isChecked = false
        binding.cbPagadas.isChecked = false
        binding.cbPlandepago.isChecked = false
        binding.cbPendientesdepago.isChecked = false
    }
}