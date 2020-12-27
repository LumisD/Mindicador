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

    override fun insertSerie(serie: SerieEntity) {
        serieDao.insertSerie(serie)
    }

    override fun deleteSeriesByCurrencyCode(currencyCode: String) {
        serieDao.deleteSeriesByCurrencyCode(currencyCode)
    }

    override fun deleteAllSeries() {
        serieDao.deleteAllSeries()
    }

    override fun getMaxIdSerie(): Int {
        return serieDao.getMaxIdSerie()
    }

    override fun getSerieCount(): Int{
        return serieDao.getSerieCount()
    }

}
