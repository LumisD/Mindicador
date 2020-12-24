package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.domain.model.SerieModel
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import com.lumisdinos.mindicador.domain.repos.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class DetailsLogicRepositoryImpl @Inject constructor(
    private val serieRepo: SerieRepository,
    private val serieStateRepo: SerieStateRepository
) : DetailsLogicRepository {

    var serieState = SerieStateModel()
    private var serie: SerieModel? = null


    override fun getSerieState(currencyId: Int): Flow<SerieStateModel> {
        Timber.d("qwer getSerieState")
        val state = serieStateRepo.getSerieState(currencyId)
        return state
    }

    override fun downloadSeries(currencyId: Int) {
        Timber.d("qwer downloadSeries")
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