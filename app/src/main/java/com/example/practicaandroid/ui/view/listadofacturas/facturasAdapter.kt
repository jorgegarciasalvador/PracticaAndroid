package com.example.practicaandroid.ui.view.listadofacturas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaandroid.R
import com.example.practicaandroid.databinding.FacturaDetailRecyclerviewBinding
import com.example.practicaandroid.model.FacturaModel

class facturasAdapter(
    private val facturas: List<FacturaModel>,
) :
    RecyclerView.Adapter<facturasAdapter.ViewHolder>() {
    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val binding = FacturaDetailRecyclerviewBinding.bind(view)

        fun bind(factura: FacturaModel) {
            binding.tvImporteFactura.text = factura.importe.toString()
            binding.tvFechaFactura.text = factura.fecha
            when (factura.estado) {
                "Pendiente de pago" -> {
                    binding.tvEstadoFactura.text =
                        getString(binding.tvEstadoFactura.context, R.string.facturadetail_rv_pendienteDePago_text)
                    binding.tvEstadoFactura.visibility = View.VISIBLE
                    binding.tvEstadoFactura.setTextColor(
                        getColor(
                            binding.tvEstadoFactura.context,
                            R.color.red
                        )
                    )
                }

                "Anulada" -> {
                    binding.tvEstadoFactura.text =
                        getString(binding.tvEstadoFactura.context, R.string.activity_filtros_tv_anuladas_text)
                    binding.tvEstadoFactura.visibility = View.VISIBLE
                    binding.tvEstadoFactura.setTextColor(
                        getColor(
                            binding.tvEstadoFactura.context,
                            R.color.grey
                        )
                    )
                }

                "Pagada" -> {
                    binding.tvEstadoFactura.text =
                        getString(binding.tvEstadoFactura.context, R.string.activity_filtros_tv_pagadas_text)
                    binding.tvEstadoFactura.visibility = View.VISIBLE
                    binding.tvEstadoFactura.setTextColor(
                        getColor(
                            binding.tvEstadoFactura.context,
                            R.color.greenDefaultApp
                        )
                    )
                }

                "Cuota fija" -> {
                    binding.tvEstadoFactura.text =
                        getString(binding.tvEstadoFactura.context, R.string.activity_filtros_tv_cuota_fija_text)
                    binding.tvEstadoFactura.visibility = View.VISIBLE
                    binding.tvEstadoFactura.setTextColor(
                        getColor(
                            binding.tvEstadoFactura.context,
                            R.color.grey
                        )
                    )
                }

                "Plan de pago" -> {
                    binding.tvEstadoFactura.text =
                        getString(binding.tvEstadoFactura.context, R.string.activity_filtros_tv_plan_de_pago_text)
                    binding.tvEstadoFactura.visibility = View.VISIBLE
                    binding.tvEstadoFactura.setTextColor(
                        getColor(
                            binding.tvEstadoFactura.context,
                            R.color.grey
                        )
                    )
                }

                else -> {
                    binding.tvEstadoFactura.text = ""
                    binding.tvEstadoFactura.visibility = View.INVISIBLE
                }
            }

            binding.ivMasDetallesFactura.setOnClickListener {

                val builder: AlertDialog.Builder =
                    AlertDialog.Builder(binding.ivMasDetallesFactura.context)
                builder.setTitle(
                    getString(
                        binding.ivMasDetallesFactura.context,
                        R.string.popup_not_working_titulo_informacion_text
                    )
                )
                    .setMessage(
                        getString(
                            binding.ivMasDetallesFactura.context,
                            R.string.popup_not_working_funcionalidad_no_disponible_text
                        )
                    )
                    .setNegativeButton("Cerrar") { _, _ ->

                    }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.factura_detail_recyclerview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(facturas[position])
    }

    override fun getItemCount(): Int {
        return facturas.size
    }
}