package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.SerieModel

interface SerieRepository {

    suspend fun getSerieForMonth(currencyCode: String, forceUpdate: Boolean): List<SerieModel>

    suspend fun replaceSeriesByCurrencyCode(currencyCode: String, series: List<SerieModel>)

    suspend fun insertAllSeries(series: List<SerieModel>)

    suspend fun insertSerie(serie: SerieModel)

    suspend fun deleteSeriesByCurrencyCode(currencyCode: String)

    suspend fun deleteAllSeries()

    suspend fun getSerieCount(): Int
}