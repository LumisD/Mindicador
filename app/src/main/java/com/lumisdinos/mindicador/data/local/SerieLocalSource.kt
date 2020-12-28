package com.lumisdinos.mindicador.data.local

import com.lumisdinos.mindicador.data.local.model.SerieEntity

interface SerieLocalSource {

    fun getSeriesByCurrencyCode(currencyCode: String): List<SerieEntity>

    fun replaceSeriesByCurrencyCode(currencyCode: String, series: List<SerieEntity>)

    fun insertAllSeries(series: List<SerieEntity>)
}