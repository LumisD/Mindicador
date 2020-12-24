package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.data.local.SerieLocalSource
import com.lumisdinos.mindicador.data.mapper.SerieDataMapper
import com.lumisdinos.mindicador.data.remote.SerieRemoteSource
import com.lumisdinos.mindicador.domain.model.SerieModel
import com.lumisdinos.mindicador.domain.repos.SerieRepository
import timber.log.Timber
import javax.inject.Inject

class SerieDataRepository @Inject constructor(
    private val serieRemote: SerieRemoteSource,
    private val serieLocal: SerieLocalSource,
    private val serieMapper: SerieDataMapper
) : SerieRepository {

    override suspend fun getSeriesByCurrencyId(currencyId: Int): List<SerieModel> {
        Timber.d("qwer getSeriesByCurrencyId")
        val remoteSeries = serieRemote.getSeriesByCurrencyId(currencyId)
        if (remoteSeries == null) {
            //todo: show message?
            return serieLocal.getSeriesByCurrencyId(currencyId)
                .map { with(serieMapper) { it.fromEntityToDomain() } }
        } else {
            return remoteSeries.map { with(serieMapper) { it.fromDataToDomain() } }//todo: pass id and currency id?
            //todo: save in db
        }
    }

    override suspend fun getSerie(serieId: Int): SerieModel? {
        return serieLocal.getSerie(serieId)?.let { with(serieMapper){it.fromEntityToDomain()} }
    }

    override suspend fun insertAllSeries(series: List<SerieModel>) {
        serieLocal.insertAllSeries(series.map { with(serieMapper) { it.fromDomainToEntity() } })
    }

    override suspend fun insertSerie(serie: SerieModel) {
        serieLocal.insertSerie(serie.let { with(serieMapper) { it.fromDomainToEntity() } })
    }

    override suspend fun deleteSerie(serieId: Int) {
        serieLocal.deleteSerie(serieId)
    }

    override suspend fun deleteSeriesByCurrencyId(currencyId: Int) {
        serieLocal.deleteSeriesByCurrencyId(currencyId)
    }

    override suspend fun deleteAllSeries() {
        serieLocal.deleteAllSeries()
    }

    override suspend fun getMaxIdSerie(): Int {
        return serieLocal.getMaxIdSerie()
    }

    override suspend fun getSerieCount(): Int {
        return serieLocal.getSerieCount()
    }

}