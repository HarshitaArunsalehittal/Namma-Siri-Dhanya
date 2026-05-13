package com.example.nammasiridhanya.api

import retrofit2.http.GET
import retrofit2.http.Query

interface MilletApi {

    @GET("resource/9ef84268-d588-465a-a308-a864a43d0070")
    suspend fun getPrices(

        @Query("api-key")
        apiKey: String,

        @Query("format")
        format: String = "json",

        @Query("limit")
        limit: Int = 100

    ): ApiResponse
}