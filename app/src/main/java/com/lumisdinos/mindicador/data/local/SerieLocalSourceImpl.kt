package com.lumisdinos.mindicador.data.local

import com.lumisdinos.mindicador.data.local.model.SerieEntity
import javax.inject.Inject

class SerieLocalSourceImpl @Inject constructor(
    private val serieDao: SerieDao
) : SerieLocalSource {

    override fun getSeriesByCurrencyCode(currencyCode: String): List<SerieEntity> {
        return serieDao.getSeriesByCurrencyCode(currencyCode)
    }

    override fun replaceSeriesByCurrencyCode(currencyCode: String, series: List<SerieEntity>) {
        serieDao.deleteSeriesByCurrencyCode(currencyCode)
        insertAllSeries(series)
    }

    override fun insertAllSeries(series: List<SerieEntity>) {
        serieDao.insertAllSeries(series)
    }
}
