package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.data.local.SerieDao
import com.lumisdinos.mindicador.data.mapper.SerieDataMapper
import com.lumisdinos.mindicador.data.mapper.SerieStateDataMapper
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import com.lumisdinos.mindicador.domain.repos.SerieStateRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SerieStateRepositoryImpl @Inject constructor(
    private val serieDao: SerieDao,
    private val serieDataMapper: SerieDataMapper,
    private val serieStateDataMapper: SerieStateDataMapper
) : SerieStateRepository {

    override fun getSerieStateFlow(currencyCode: String): Flow<SerieStateModel> {

        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                val list = serieDao.getAllSerieState()
                Timber.d("qwer getSerieStateFlow list: %s", list)
            }
        }

        Timber.d("qwer getSerieStateFlow: %s", currencyCode)
        return serieDao.getSerieStateFlow(currencyCode).map {
            if (it == null) {

                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        val state = serieDao.getSerieState(currencyCode)
                        Timber.d("qwer getSerieStateFlow   getSerieState: %s", state)
                    }
                }

                Timber.d("qwer getSerieStateFlow if (it == null) -> new SerieStateModel")
                SerieStateModel(codigo = currencyCode)
            } else {
                Timber.d("qwer getSerieStateFlow if (it NOT null) -> mapper")
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