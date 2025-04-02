package com.example.mad411

import retrofit2.http.GET

interface CurrencyApiService{
    @GET("CAD")
    suspend fun getCurrency(): CurrencyApiObj
}