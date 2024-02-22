package com.example.practicaandroid.di

import android.content.Context
import androidx.room.Room
import com.example.practicaandroid.data.database.FacturasDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOTE_DATABASE_NAME = "facturas_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): FacturasDatabase {
        return Room.databaseBuilder(context, FacturasDatabase::class.java, QUOTE_DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }


    @Singleton
    @Provides
    fun provideQuoteDao(db: FacturasDatabase) = db.getFacturasDao()
}