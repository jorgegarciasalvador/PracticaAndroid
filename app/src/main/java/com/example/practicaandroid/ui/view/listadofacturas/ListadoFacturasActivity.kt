package com.example.practicaandroid.ui.view.listadofacturas

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaandroid.R
import com.example.practicaandroid.databinding.ActivityListadofacturasBinding
import com.example.practicaandroid.model.FacturaModel
import com.example.practicaandroid.ui.view.filtros.FiltrosActivity
import com.example.practicaandroid.ui.viewmodel.ViewModelFacturas
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class ListadoFacturasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListadofacturasBinding
    private val facturasViewModel: ViewModelFacturas by viewModels()
    private lateinit var facturasAdapter: facturasAdapter
    private var retromockActivado by Delegates.notNull<Boolean>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadofacturasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retromockActivado = intent.getBooleanExtra("retromockactivado", false)

        facturasViewModel.onCreate(retromockActivado)

        facturasViewModel.facturas_.observe(this) {
            setUpRecyclerView(it)
        }

        binding.toolbar.binding.iconoDerecha.setOnClickListener {
            facturasViewModel.onCreate(retromockActivado)
            onClickFiltros()
        }

        binding.toolbar.binding.iconoIzquierda.setOnClickListener {
            finish()
        }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == -1) {
            result.data.let {
                facturasViewModel.filtrarFacturas(
                    result.data!!.getStringExtra("estado")!!,
                    result.data!!.getFloatExtra("importe", 0F),
                    result.data!!.getStringExtra("fechaInferior")!!,
                    result.data!!.getStringExtra("fechaSuperior")!!
                )
            }
        }else{
            facturasViewModel.onCreate(retromockActivado)
        }
    }

    override fun onRestart() {
        super.onRestart()
        facturasViewModel.facturas_.observe(this) {
            setUpRecyclerView(it)
        }
    }

    private fun setUpRecyclerView(facturas: List<FacturaModel>) {
        facturasAdapter = facturasAdapter(facturas)

        val recyclerView: RecyclerView = binding.rvListadoFacturas
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = facturasAdapter
        if (facturas.isEmpty()){
            val builder: AlertDialog.Builder =
                AlertDialog.Builder(this)
            builder.setTitle(
                ContextCompat.getString(
                    this,
                    R.string.popup_info_autoconsumo_titulo_text
                )
            )
                .setMessage(
                    ContextCompat.getString(
                       this,
                        R.string.filtro_vacio_text
                    )
                )
                .setNegativeButton("Cerrar") { _, _ ->

                }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onClickFiltros() {
        val intent = Intent(this, FiltrosActivity::class.java)
        resultLauncher.launch(intent)
    }
}