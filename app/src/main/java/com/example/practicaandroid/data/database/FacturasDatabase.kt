package com.example.practicaandroid.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicaandroid.data.dao.FacturasDao
import com.example.practicaandroid.model.FacturaEntity


@Database(entities = [FacturaEntity::class], version = 1, exportSchema = false)
abstract class FacturasDatabase : RoomDatabase() {
    abstract fun getFacturasDao(): FacturasDao
}