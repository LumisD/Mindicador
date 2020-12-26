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
    suspend fun getAllCurrencies(): Response<ResponseBody>//Response<Map<String, Any>>

    @GET("/api/{currencyType}")
    suspend fun getCurrencyForMonth(@Path("currencyType") currencyType: String): List<SerieEntry>//Response<List<SerieEntry>>

}