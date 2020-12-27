package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.data.local.SerieDao
import com.lumisdinos.mindicador.data.mapper.SerieDataMapper
import com.lumisdinos.mindicador.data.mapper.SerieStateDataMapper
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import com.lumisdinos.mindicador.domain.repos.SerieStateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SerieStateRepositoryImpl @Inject constructor(
    private val serieDao: SerieDao,
    private val serieDataMapper: SerieDataMapper,
    private val serieStateDataMapper: SerieStateDataMapper
) : SerieStateRepository {

    override fun getSerieStateFlow(currencyCode: String): Flow<SerieStateModel> {
        return serieDao.getSerieStateFlow(currencyCode).map {
            if (it == null) {
                SerieStateModel()
            } else {
                with(serieStateDataMapper) { it.fromEntityToDomain() }
            }
        }
    }

    override fun getSerieState(currencyCode: String): SerieStateModel? {
        return serieDao.getSerieState(currencyCode)?.let { with(serieStateDataMapper) {it.fromEntityToDomain()} }
    }

    override fun insertSerieState(serieState: SerieStateModel)  =
        serieDao.insertSerieState(with(serieStateDataMapper) { serieState.fromDomainToEntity() })

    override fun deleteSerieState(currencyCode: String) {
        serieDao.deleteSerieState(currencyCode)
    }

    override fun deleteAllSerieStates() = serieDao.deleteAllSerieStates()

}