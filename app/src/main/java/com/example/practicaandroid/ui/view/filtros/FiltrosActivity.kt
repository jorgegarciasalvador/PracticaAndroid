package com.example.practicaandroid.ui.view.filtros

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaandroid.databinding.ActivityFiltrosBinding
import com.google.android.material.slider.Slider
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FiltrosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFiltrosBinding
    private val calendar = Calendar.getInstance()
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiltrosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("practicaAndroidPreferences", MODE_PRIVATE)

        setUpPrefrerences()
        setUpListeners()

    }

    override fun onResume() {
        super.onResume()
        setUpPrefrerences()
    }

    private fun setUpPrefrerences() {
        binding.etFechaDesde.setText(sharedPreferences.getString("fechaDesde", "dd/mm/yyyy"))
        binding.etFechaHasta.setText(sharedPreferences.getString("fechaHasta", "dd/mm/yyyy"))
        binding.sliderImporte.value = sharedPreferences.getFloat("importeSlider", 100F)
        binding.tvImporteSlider.text = sharedPreferences.getString("textoImporteSlider", "100")
        binding.cbPlandepago.isChecked = sharedPreferences.getBoolean("plandepago", false)
        binding.cbPagadas.isChecked = sharedPreferences.getBoolean("pagadas", false)
        binding.cbPendientesdepago.isChecked =
            sharedPreferences.getBoolean("pendientesdepago", false)
        binding.cbAnuladas.isChecked = sharedPreferences.getBoolean("anuladas", false)
        binding.cbCuotafija.isChecked = sharedPreferences.getBoolean("cuotafija", false)
    }

    private fun setUpListeners() {
        binding.sliderImporte.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                binding.tvImporteSlider.text = binding.sliderImporte.value.toInt().toString()
            }

            override fun onStopTrackingTouch(slider: Slider) {
                binding.tvImporteSlider.text = binding.sliderImporte.value.toInt().toString()
            }
        })

        binding.etFechaDesde.setOnClickListener {
            showDatePicker(it as EditText)
        }
        binding.etFechaHasta.setOnClickListener {
            showDatePicker(it as EditText)
        }

        binding.btnEliminarFiltros.setOnClickListener {
            clearFilters()
        }

        binding.toolbar.binding.iconoDerecha.setOnClickListener {
            onClickClose()
        }

        binding.btnAplicar.setOnClickListener {
            aplicarFiltros()
        }
    }

    private fun aplicarFiltros(){
        Toast.makeText(this,"Me falta terminar este boton para que filtre de verdad", Toast.LENGTH_SHORT).show()
    }
    private fun showDatePicker(view: EditText) {
        val datePickerDialog = DatePickerDialog(
            this,
            { DatePicker, year: Int, montOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, montOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd//MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                view.setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()

    }

    private fun clearFilters() {
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

    private fun onClickClose() {
        val editorPreferences = sharedPreferences.edit()
        editorPreferences.putString("fechaDesde", binding.etFechaDesde.text.toString())
        editorPreferences.putString("fechaHasta", binding.etFechaHasta.text.toString())
        editorPreferences.putFloat("importeSlider", binding.sliderImporte.value)
        editorPreferences.putString("textoImporteSlider", binding.tvImporteSlider.text.toString())
        editorPreferences.putBoolean("anuladas", binding.cbAnuladas.isChecked)
        editorPreferences.putBoolean("cuotafija", binding.cbCuotafija.isChecked)
        editorPreferences.putBoolean("pagadas", binding.cbPagadas.isChecked)
        editorPreferences.putBoolean("plandepago", binding.cbPlandepago.isChecked)
        editorPreferences.putBoolean("pendientesdepago", binding.cbPendientesdepago.isChecked)
        editorPreferences.apply()
        finish()
    }
}