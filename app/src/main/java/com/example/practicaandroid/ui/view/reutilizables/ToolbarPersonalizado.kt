package com.example.practicaandroid.ui.view.reutilizables

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import com.example.practicaandroid.R

class ToolbarPersonalizado(
    context: Context,
    attrs: AttributeSet
) : Toolbar(context, attrs) {



    init {
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.ToolbarPersonalizado, 0, 0)

        val iconoIzquierda =
            attributes.getResourceId(R.styleable.ToolbarPersonalizado_icono_izquierda, 0)
        val textoIzquierda = attributes.getString(R.styleable.ToolbarPersonalizado_texto_izquierda)
        val iconoDerecha =
            attributes.getResourceId(R.styleable.ToolbarPersonalizado_icono_derecha, 0)
        val textoTitulo = attributes.getString(R.styleable.ToolbarPersonalizado_texto_titulo)
        //val onClickIconoDerecha

        attributes.recycle()

        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.toolbar_personalizado, this, true)

        val nuevoIconoIzquierda = findViewById<ImageView>(R.id.icono_izquierda)
        nuevoIconoIzquierda.setImageResource(iconoIzquierda)
        val nuevoTextoIzquierda = findViewById<TextView>(R.id.texto_izquierda)
        nuevoTextoIzquierda.text = textoIzquierda
        val nuevoIconoDerecha = findViewById<ImageView>(R.id.icono_derecha)
        nuevoIconoDerecha.setImageResource(iconoDerecha)
        //nuevoIconoDerecha.setOnClickListener { onClickIconoDerecha }
        val nuevoTextoTitulo = findViewById<TextView>(R.id.toolbar_title)
        nuevoTextoTitulo.text = textoTitulo
    }
}