package com.example.practicaandroid.ui.view.listadofacturas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaandroid.R
import com.example.practicaandroid.model.FacturaModel
import com.example.practicaandroid.databinding.FacturaDetailRecyclerviewBinding
import com.example.practicaandroid.ui.viewmodel.ViewModelFacturas

class facturasAdapter(
    private val facturas: List<FacturaModel>,
    private val context: Context
) :
    RecyclerView.Adapter<facturasAdapter.ViewHolder>() {

    class ViewHolder(binding: FacturaDetailRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val importeFactura = binding.tvImporteFactura
        val estadoFactura = binding.tvEstadoFactura
        val fechaFactura = binding.tvFechaFactura
        val masDetalles = binding.ivMasDetallesFactura

        fun bind(factura: FacturaModel, context: Context) {
            importeFactura.text = factura.importe.toString()
            fechaFactura.text = factura.fecha
            if (factura.estado == "Pendiente de pago") {
                estadoFactura.text = getString(context, R.string.pendienteDePago_text)
                estadoFactura.visibility = View.VISIBLE
            } else {
                estadoFactura.text = ""
                estadoFactura.visibility = View.INVISIBLE
            }

            masDetalles.setOnClickListener {
                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder.setTitle(getString(context, R.string.informacion_text))
                    .setMessage(getString(context, R.string.mensaje_text))
                    .setNegativeButton("Cerrar") { _, _ ->

                    }
                val dialog:AlertDialog = builder.create()
                dialog.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FacturaDetailRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(facturas[position], context)
    }

    override fun getItemCount(): Int {
        return facturas.size
    }
}