package com.example.practicaandroid.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class FacturaModel(
    @SerializedName("fecha") val fecha: String,
    @SerializedName("descEstado") val estado: String,
    @SerializedName("importeOrdenacion") val importe: Double
)

fun FacturaEntity.toDomain() = FacturaModel(fecha, estado, importe)
data class respuestaApiFacturas(
    @SerializedName("numFacturas") val numFacturas: Int,
    @SerializedName("facturas") val facturas: List<FacturaModel>
)

@Entity(tableName = "facturas_table")
data class FacturaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "fecha") val fecha: String,
    @ColumnInfo(name = "descEstado") val estado: String,
    @ColumnInfo(name = "importeOrdenacion") val importe: Double,
)

fun FacturaModel.toDatabase() = FacturaEntity(fecha = fecha, estado = estado, importe = importe)
