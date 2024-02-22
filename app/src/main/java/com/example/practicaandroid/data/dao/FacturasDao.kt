package com.example.practicaandroid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicaandroid.model.FacturaEntity

@Dao
interface FacturasDao {
    @Query("Select * FROM facturas_table ORDER BY fecha DESC")
    suspend fun getAllFacturas(): List<FacturaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(facturas: List<FacturaEntity>)

    @Query("DELETE FROM facturas_table")
    suspend fun deleteAllFacturas()
}