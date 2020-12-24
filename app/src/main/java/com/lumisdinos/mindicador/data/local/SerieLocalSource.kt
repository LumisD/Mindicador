package com.lumisdinos.mindicador.data.local

import com.lumisdinos.mindicador.data.local.model.SerieEntity

interface SerieLocalSource {

    fun getSeriesByCurrencyId(currencyId: Int): List<SerieEntity>

    fun getSerie(serieId: Int): SerieEntity?

    fun insertAllSeries(series: List<SerieEntity>)

    fun insertSerie(serie: SerieEntity)

    fun deleteSerie(serieId: Int)

    fun deleteSeriesByCurrencyId(currencyId: Int)

    fun deleteAllSeries()

    fun getMaxIdSerie(): Int

    fun getSerieCount(): Int

}