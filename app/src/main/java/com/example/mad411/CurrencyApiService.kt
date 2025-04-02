package com.example.mad411

import com.example.mad411.Currency
import retrofit2.http.GET

interface CurrencyApiService{
    @GET("all")
    suspend fun getCurrency(): List<Currency>
}