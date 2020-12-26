package com.lumisdinos.mindicador.data.remote

import com.lumisdinos.mindicador.data.remote.model.SerieEntry
import retrofit2.Response


interface SerieRemoteSource {

    suspend fun getSeriesByCurrencyId(currencyId: Int): List<SerieEntry>?//Response<List<SerieEntry>>?

}