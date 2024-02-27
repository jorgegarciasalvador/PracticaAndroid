package com.example.practicaandroid.ui.view.filtros

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaandroid.databinding.ActivityFiltrosBinding
import com.example.practicaandroid.ui.view.listadofacturas.ListadoFacturasActivity
import com.google.android.material.slider.Slider
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpPrefrerences() {
        binding.etFechaDesde.setText(
            sharedPreferences.getString(
                "fechaDesde", LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            )
        )
        binding.etFechaHasta.setText(
            sharedPreferences.getString(
                "fechaHasta", LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            )
        )
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
            val intent = Intent(this, ListadoFacturasActivity::class.java)
            setResult(Activity.RESULT_CANCELED, intent)
            onClickClose()
        }

        binding.btnAplicar.setOnClickListener {
            aplicarFiltros()
        }
    }

    private fun aplicarFiltros() {
        val importe = binding.sliderImporte.value
        val fechaSuperior = binding.etFechaHasta.text.toString()
        val fechaInferior = binding.etFechaDesde.text.toString()
        var estado = ""
        var checkboxschecked = 0
        for (i in 0 until binding.layoutChecboxContainer.childCount) {
            val view: View = binding.layoutChecboxContainer.getChildAt(i)
            if (view is LinearLayout) {
                for (j in 0 until view.childCount) {
                    val view2: View = view.getChildAt(j)
                    if (view2 is CheckBox && view2.isChecked) {
                        checkboxschecked++
                        estado = (view.getChildAt(j + 1) as TextView).text.toString()
                    }
                }
            }
        }

        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaInferiorFormateada = formatter.parse(fechaInferior)
        val fechaSuperiorFormateada = formatter.parse(fechaSuperior)
        if (fechaInferiorFormateada != null) {
            if (fechaInferiorFormateada.compareTo(fechaSuperiorFormateada) > 0) {
                Toast.makeText(this, "Revisa las fechas seleccionadas, puede ser que quiera crear un bucle espacio-temporal", Toast.LENGTH_SHORT).show()
            } else if (checkboxschecked > 1) {
                Toast.makeText(
                    this,
                    "No puedes seleccionar varios checks de estado a la vez",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(this, ListadoFacturasActivity::class.java)
                intent.putExtra("estado", estado)
                intent.putExtra("importe", importe)
                intent.putExtra("fechaSuperior", fechaSuperior)
                intent.putExtra("fechaInferior", fechaInferior)

                setResult(Activity.RESULT_OK, intent)
                onClickClose()
            }
        }
    }

    private fun showDatePicker(view: EditText) {
        val datePickerDialog = DatePickerDialog(
            this,
            { DatePicker, year: Int, montOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, montOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                view.setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun clearFilters() {
        binding.etFechaDesde.setText(
            LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        )
        binding.etFechaHasta.setText(
            LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        )
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