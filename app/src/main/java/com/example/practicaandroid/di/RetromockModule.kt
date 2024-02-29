package com.example.practicaandroid.di

import co.infinum.retromock.Retromock
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetromockModule {

    @Singleton
    @Provides
    fun provideRetromock(): Retromock {
        return Retromock.Builder().retrofit(NetworkModule.provideRetrofit()).build()
    }
}