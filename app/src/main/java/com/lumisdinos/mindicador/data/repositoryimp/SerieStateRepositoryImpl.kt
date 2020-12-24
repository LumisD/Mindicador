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

    override fun getSerieState(currencyId: Int): Flow<SerieStateModel> {
        return serieDao.getSerieState().map {
            if (it == null) {
                SerieStateModel()
            } else {
                val currencies = serieDao.getSeriesByCurrencyId(currencyId).map { with(serieDataMapper) {it.fromEntityToDomain()} }
                with(serieStateDataMapper) { it.fromEntityToDomain(currencies) }
            }
        }
    }

    override fun insertSerieState(serieState: SerieStateModel)  =
        serieDao.insertSerieState(with(serieStateDataMapper) { serieState.fromDomainToEntity() })

    override fun deleteAllSerieStates() = serieDao.deleteAllSerieStates()

}