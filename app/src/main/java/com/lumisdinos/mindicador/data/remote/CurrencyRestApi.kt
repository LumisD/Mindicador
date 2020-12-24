package com.lumisdinos.mindicador.data.remote

import com.lumisdinos.mindicador.data.remote.model.CurrencyEntry
import com.lumisdinos.mindicador.data.remote.model.SerieEntry
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyRestApi {

    @GET("/api")
    suspend fun getAllCurrencies(): List<CurrencyEntry>

    @GET("/api/{currencyType}")
    suspend fun getCurrencyForMonth(@Path("currencyType") currencyType: String): List<SerieEntry>

}