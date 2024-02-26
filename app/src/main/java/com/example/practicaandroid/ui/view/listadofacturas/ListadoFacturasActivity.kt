package com.example.practicaandroid.ui.view.listadofacturas

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaandroid.databinding.ActivityListadofacturasBinding
import com.example.practicaandroid.model.FacturaModel
import com.example.practicaandroid.ui.view.filtros.FiltrosActivity
import com.example.practicaandroid.ui.viewmodel.ViewModelFacturas
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListadoFacturasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListadofacturasBinding
    private val facturasViewModel: ViewModelFacturas by viewModels()
    private lateinit var facturasAdapter: facturasAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadofacturasBinding.inflate(layoutInflater)
        setContentView(binding.root)


        facturasViewModel.onCreate()

        facturasViewModel.facturas_.observe(this) {
            setUpRecyclerView(it)
        }

        binding.toolbar.binding.iconoDerecha.setOnClickListener {
            onClickFiltros()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == -1) {
            data.let {
                facturasViewModel.filtrar(
                    data!!.getStringExtra("estado")!!,
                    data.getFloatExtra("importe", 0F),
                    data.getStringExtra("fechaInferior")!!,
                    data.getStringExtra("fechaSuperior")!!
                )
            }
        }else{
            facturasViewModel.onCreate()
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
    }

    private fun onClickFiltros() {
        val intent = Intent(this, FiltrosActivity::class.java)
        startActivityForResult(intent, 1)
    }
}