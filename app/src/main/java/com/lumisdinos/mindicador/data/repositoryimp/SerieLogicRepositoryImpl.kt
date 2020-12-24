package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.domain.model.SerieModel
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import com.lumisdinos.mindicador.domain.repos.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class SerieLogicRepositoryImpl @Inject constructor(
    private val serieRepo: SerieRepository,
    private val serieStateRepo: SerieStateRepository
) : SerieLogicRepository {

    var serieState = SerieStateModel()
    private var currency: SerieModel? = null

    override fun getSerieState(currencyId: Int): Flow<SerieStateModel> {
        Timber.d("qwer getSerieState")
        val state = serieStateRepo.getSerieState(currencyId)//todo: pass currencyId
        return state
    }

    override fun downloadSeriesByCurrencyId(currencyId: Int) {
        Timber.d("qwer downloadSeriesByCurrencyId")
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                serieRepo.getSeriesByCurrencyId(currencyId)
            }
        }
    }

    override fun share() {
        Timber.d("qwer share")
    }

    override fun seriesAreRendered() {
        Timber.d("qwer seriesAreRendered")
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) { setStateSeries(serieState.series, false) }
        }
    }

    private suspend fun setStateSeries(series: List<SerieModel>, isSeriesNew: Boolean = true) {
        Timber.d("qwer setStateSeries")
        withContext(Dispatchers.IO) {
            serieState = serieState.copy(series = series, isSeriesUpdated = isSeriesNew)
            serieStateRepo.insertSerieState(serieState)
        }
    }

}