package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.common.ResourceState
import com.lumisdinos.mindicador.data.local.SerieLocalSource
import com.lumisdinos.mindicador.data.mapper.SerieDataMapper
import com.lumisdinos.mindicador.data.remote.SerieRemoteSource
import com.lumisdinos.mindicador.domain.model.SerieModel
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import com.lumisdinos.mindicador.domain.repos.SerieRepository
import com.lumisdinos.mindicador.domain.repos.SerieStateRepository
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
        if (forceUpdate) {
            val remoteResponse = serieRemote.getSerieForMonth(currencyCode)
            val isRemoteError = remoteResponse.state == ResourceState.ERROR
            val list: MutableList<SerieModel> = mutableListOf()
            if (isRemoteError) {
                list.addAll(serieLocal.getSeriesByCurrencyCode(currencyCode)
                    .map { with(serieMapper) { it.fromEntityToDomain() } })
                updateSerieState(currencyCode, remoteResponse.message)
            } else {
                remoteResponse.data?.let {
                    list.addAll(it.map { with(serieMapper) { it.fromDataToDomain(currencyCode) } })
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

}