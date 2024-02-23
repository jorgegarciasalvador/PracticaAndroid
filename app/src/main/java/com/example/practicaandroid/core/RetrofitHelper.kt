package com.example.practicaandroid.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://viewnextandroid3.wiremockapi.cloud/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}