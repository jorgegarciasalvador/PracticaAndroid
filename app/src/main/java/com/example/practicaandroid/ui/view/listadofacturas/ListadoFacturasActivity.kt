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

        facturasViewModel.facturas.observe(this) {
            setUpRecyclerView(it)
        }

        binding.toolbar.binding.iconoDerecha.setOnClickListener {
            onClickFiltros()
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
        startActivity(intent)
    }
}