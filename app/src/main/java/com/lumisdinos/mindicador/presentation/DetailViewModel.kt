package com.lumisdinos.mindicador.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lumisdinos.mindicador.domain.model.SerieModel
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import com.lumisdinos.mindicador.domain.repos.SerieLogicRepository
import com.lumisdinos.mindicador.ui.mapper.SerieViewMapper
import com.lumisdinos.mindicador.ui.model.SerieView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import timber.log.Timber
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val serieLogicRepo: SerieLogicRepository,
    private val serieViewMapper: SerieViewMapper
) : ViewModel() {

    @ExperimentalCoroutinesApi
    val serieState: LiveData<SerieStateModel> = serieLogicRepo
        .getSerieState(0)//todo: pass meaningfull currencyId
        .catch {
            Timber.d("qwer getSerieState catch: %s", it.message)
        }
        .asLiveData()

    fun downloadSeriesByCurrencyId(currencyCode: String) {
        //serieLogicRepo.downloadSeriesByCurrencyId(currencyId)
    }

    fun share() {
        serieLogicRepo.share()
    }

    fun seriesAreRendered() {
        serieLogicRepo.seriesAreRendered()
    }

    fun convertSerieyModelsToSerieViews(serieModels: List<SerieModel>): List<SerieView> {
        return serieModels.map { with(serieViewMapper) { it.fromDomainToView() } }
    }
}