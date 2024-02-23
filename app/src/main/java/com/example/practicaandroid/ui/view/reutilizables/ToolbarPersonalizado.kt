package com.example.practicaandroid.ui.view.reutilizables

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toolbar
import com.example.practicaandroid.R
import com.example.practicaandroid.databinding.ToolbarPersonalizadoBinding

class ToolbarPersonalizado(
    context: Context,
    attrs: AttributeSet
) : Toolbar(context, attrs) {


    var binding: ToolbarPersonalizadoBinding

    init {
        binding = ToolbarPersonalizadoBinding.inflate(LayoutInflater.from(context),this)
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.ToolbarPersonalizado, 0, 0)

        val nuevoIconoIzquierda =
            attributes.getResourceId(R.styleable.ToolbarPersonalizado_icono_izquierda, 0)
        val nuevoTextoIzquierda = attributes.getString(R.styleable.ToolbarPersonalizado_texto_izquierda)
        val nuevoIconoDerecha =
            attributes.getResourceId(R.styleable.ToolbarPersonalizado_icono_derecha, 0)
        val nuevoTextoTitulo = attributes.getString(R.styleable.ToolbarPersonalizado_texto_titulo)

        attributes.recycle()

        binding.iconoIzquierda.setImageResource(nuevoIconoIzquierda)
        binding.textoIzquierda.text = nuevoTextoIzquierda
        binding.iconoDerecha.setImageResource(nuevoIconoDerecha)
        binding.toolbarTitle.text = nuevoTextoTitulo
    }
}