package com.example.nammasiridhanya.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: MilletApi by lazy {

        Retrofit.Builder()
            .baseUrl("https://api.data.gov.in/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(MilletApi::class.java)
    }
}