package com.lumisdinos.mindicador.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyRestApi {

    @GET("/api")
    suspend fun getAllCurrencies(): Response<ResponseBody>

    @GET("/api/{currencyCode}")
    suspend fun getCurrencyForMonth(@Path("currencyCode") currencyCode: String): Response<ResponseBody>

}