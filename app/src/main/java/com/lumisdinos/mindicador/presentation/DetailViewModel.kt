package com.lumisdinos.mindicador.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import com.lumisdinos.mindicador.domain.model.SerieModel
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import com.lumisdinos.mindicador.domain.repos.SerieLogicRepository
import com.lumisdinos.mindicador.domain.repos.SerieRepository
import com.lumisdinos.mindicador.domain.repos.SerieStateRepository
import com.lumisdinos.mindicador.ui.mapper.SerieViewMapper
import com.lumisdinos.mindicador.ui.model.SerieView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import timber.log.Timber
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val serieLogicRepo: SerieLogicRepository,
    private val serieRepo: SerieRepository,
    private val serieStateRepo: SerieStateRepository,
    private val serieViewMapper: SerieViewMapper
) : ViewModel() {

    private var currCode = ""

    @ExperimentalCoroutinesApi
    val serieState: LiveData<SerieStateModel> = serieStateRepo
        .getSerieStateFlow(currCode)
        .catch {
            Timber.d("qwer getSerieStateFlow catch: %s", it.message)
        }
        .asLiveData()

    //var serieState: LiveData<SerieStateModel> = MutableLiveData<SerieStateModel>()
    private val _series: MutableLiveData<List<SerieModel>> = MutableLiveData<List<SerieModel>>()
    val series: LiveData<List<SerieModel>> = _series


    fun downloadSeriesByCurrencyId(currencyCode: String = currCode) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                currCode = currencyCode
                //setSerieState(currencyCode)
                _series.postValue(serieRepo.getSerieForMonth(currencyCode,true))
            }
        }
    }

//    private fun setSerieState(currencyCode: String) {
//        Timber.d("qwer setSerieState")
//        @ExperimentalCoroutinesApi
//        serieState = serieStateRepo.getSerieStateFlow(currencyCode)
//            .catch { Timber.d("qwer getSerieState catch: %s", it.message) }
//            .asLiveData()
//    }

    fun share() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                serieLogicRepo.share(currCode)
            }
        }
    }

    fun messageIsShown(type: String) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                serieLogicRepo.messageIsShown(currCode, type)
            }
        }
    }

    fun convertSerieyModelsToSerieViews(serieModels: List<SerieModel>): List<SerieView> {
        return serieModels.map { with(serieViewMapper) { it.fromDomainToView() } }
    }
}