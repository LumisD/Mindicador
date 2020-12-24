package com.lumisdinos.mindicador.data.remote

import com.lumisdinos.mindicador.data.remote.model.SerieEntry


interface SerieRemoteSource {

    suspend fun getSeriesByCurrencyId(currencyId: Int): List<SerieEntry>?

}