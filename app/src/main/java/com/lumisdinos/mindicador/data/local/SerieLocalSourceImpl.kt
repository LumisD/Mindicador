package com.lumisdinos.mindicador.data.local

import com.lumisdinos.mindicador.data.local.model.SerieEntity
import javax.inject.Inject

class SerieLocalSourceImpl @Inject constructor(
    private val serieDao: SerieDao
) : SerieLocalSource {

    override fun getSeriesByCurrencyId(currencyId: Int): List<SerieEntity> {
        return serieDao.getSeriesByCurrencyId(currencyId)
    }

    override fun getSerie(serieId: Int): SerieEntity? {
        return serieDao.getSerie(serieId)
    }

    override fun insertAllSeries(series: List<SerieEntity>) {
        serieDao.insertAllSeries(series)
    }

    override fun insertSerie(serie: SerieEntity) {
        serieDao.insertSerie(serie)
    }

    override fun deleteSerie(serieId: Int) {
        serieDao.deleteSerie(serieId)
    }

    override fun deleteSeriesByCurrencyId(currencyId: Int) {
        serieDao.deleteSeriesByCurrencyId(currencyId)
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
