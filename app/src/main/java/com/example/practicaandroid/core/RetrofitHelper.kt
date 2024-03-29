package com.example.practicaandroid.core

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("https://viewnextandroid3.wiremockapi.cloud/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}