package com.lumisdinos.mindicador.data.remote

import com.lumisdinos.mindicador.common.Resource
import com.lumisdinos.mindicador.data.remote.model.SerieEntry


interface SerieRemoteSource {

    suspend fun getSerieForMonth(currencyCode: String): Resource<List<SerieEntry>>

}