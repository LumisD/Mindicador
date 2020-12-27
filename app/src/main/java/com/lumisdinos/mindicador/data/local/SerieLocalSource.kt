package com.lumisdinos.mindicador.data.local

import com.lumisdinos.mindicador.data.local.model.SerieEntity
import com.lumisdinos.mindicador.domain.model.SerieModel

interface SerieLocalSource {

    fun getSeriesByCurrencyCode(currencyCode: String): List<SerieEntity>

    fun replaceSeriesByCurrencyCode(currencyCode: String, series: List<SerieEntity>)

    fun insertAllSeries(series: List<SerieEntity>)

    fun insertSerie(serie: SerieEntity)

    fun deleteSeriesByCurrencyCode(currencyCode: String)

    fun deleteAllSeries()

    fun getMaxIdSerie(): Int

    fun getSerieCount(): Int

}