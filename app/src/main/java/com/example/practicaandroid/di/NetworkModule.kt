package com.example.practicaandroid.di

import com.example.practicaandroid.core.RetrofitHelper
import com.example.practicaandroid.data.FacturasApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return RetrofitHelper.getRetrofit()
    }

    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit): FacturasApiClient {
        return retrofit.create(FacturasApiClient::class.java)
    }
}