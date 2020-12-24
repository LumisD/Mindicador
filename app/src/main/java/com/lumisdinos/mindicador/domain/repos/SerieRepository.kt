package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.SerieModel

interface SerieRepository {

    suspend fun getSeriesByCurrencyId(currencyId: Int): List<SerieModel>

    suspend fun getSerie(serieId: Int): SerieModel?

    suspend fun insertAllSeries(series: List<SerieModel>)

    suspend fun insertSerie(serie: SerieModel)

    suspend fun deleteSerie(serieId: Int)

    suspend fun deleteSeriesByCurrencyId(currencyId: Int)

    suspend fun deleteAllSeries()

    suspend fun getMaxIdSerie(): Int

    suspend fun getSerieCount(): Int
}