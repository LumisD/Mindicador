package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.common.ResourceState
import com.lumisdinos.mindicador.data.local.SerieLocalSource
import com.lumisdinos.mindicador.data.mapper.SerieDataMapper
import com.lumisdinos.mindicador.data.remote.SerieRemoteSource
import com.lumisdinos.mindicador.domain.model.SerieModel
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import com.lumisdinos.mindicador.domain.repos.SerieRepository
import com.lumisdinos.mindicador.domain.repos.SerieStateRepository
import timber.log.Timber
import javax.inject.Inject

class SerieDataRepository @Inject constructor(
    private val serieRemote: SerieRemoteSource,
    private val serieLocal: SerieLocalSource,
    private val serieStateRepo: SerieStateRepository,
    private val serieMapper: SerieDataMapper
) : SerieRepository {

    override suspend fun getSerieForMonth(
        currencyCode: String,
        forceUpdate: Boolean
    ): List<SerieModel> {
        Timber.d("qwer getSerieForMonth")
        if (forceUpdate) {
            val remoteResponse = serieRemote.getSerieForMonth(currencyCode)
            val isRemoteError = remoteResponse.state == ResourceState.ERROR
            val list: MutableList<SerieModel> = mutableListOf()
            if (isRemoteError) {
                Timber.d("qwer getSerieForMonth isRemoteError: %s", remoteResponse.message)
                list.addAll(serieLocal.getSeriesByCurrencyCode(currencyCode)
                    .map { with(serieMapper) { it.fromEntityToDomain() } })
                updateSerieState(currencyCode, remoteResponse.message)
            } else {
                remoteResponse.data?.let {
                    Timber.d("qwer getAllCurrencies remoteResponse.data: %s", it)
                    list.addAll(it.map { with(serieMapper) { it.fromDataToDomain(currencyCode) } })
                    Timber.d("qwer getAllCurrencies list: %s", it)
                    replaceSeriesByCurrencyCode(currencyCode, list)
                }
            }
            return list
        } else {
            return serieLocal.getSeriesByCurrencyCode(currencyCode)
                .map { with(serieMapper) { it.fromEntityToDomain() } }
        }
    }

    private fun updateSerieState(currencyCode: String, message: String? = null) {
        Timber.d("qwer updateSerieState")
        var serieState = serieStateRepo.getSerieState(currencyCode)
        if (serieState == null) {
            serieState = SerieStateModel(codigo = currencyCode)
        }
        serieState = serieState.copy(
            errorMessage = message
        )
        serieStateRepo.insertSerieState(serieState)
    }

    override suspend fun replaceSeriesByCurrencyCode(
        currencyCode: String, series: List<SerieModel>
    ) {
        serieLocal.replaceSeriesByCurrencyCode(currencyCode,
            series.map { with(serieMapper) { it.fromDomainToEntity() } })
    }

    override suspend fun insertAllSeries(series: List<SerieModel>) {
        serieLocal.insertAllSeries(series.map { with(serieMapper) { it.fromDomainToEntity() } })
    }

    override suspend fun insertSerie(serie: SerieModel) {
        serieLocal.insertSerie(serie.let { with(serieMapper) { it.fromDomainToEntity() } })
    }

    override suspend fun deleteSeriesByCurrencyCode(currencyCode: String) {
        serieLocal.deleteSeriesByCurrencyCode(currencyCode)
    }

    override suspend fun deleteAllSeries() {
        serieLocal.deleteAllSeries()
    }

    override suspend fun getSerieCount(): Int {
        return serieLocal.getSerieCount()
    }

}