package com.lumisdinos.mindicador.data.remote

import com.lumisdinos.mindicador.data.remote.model.CurrencyEntry
import com.lumisdinos.mindicador.data.remote.model.SerieEntry
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyRestApi {

    @GET("/api")
    suspend fun getAllCurrencies(): Response<ResponseBody>

    @GET("/api/{currencyCode}")
    suspend fun getCurrencyForMonth(@Path("currencyCode") currencyCode: String): Response<ResponseBody>

}