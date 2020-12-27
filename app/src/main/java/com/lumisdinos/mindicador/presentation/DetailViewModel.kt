package com.lumisdinos.mindicador.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lumisdinos.mindicador.domain.model.SerieModel
import com.lumisdinos.mindicador.domain.repos.SerieLogicRepository
import com.lumisdinos.mindicador.domain.repos.SerieRepository
import com.lumisdinos.mindicador.domain.repos.SerieStateRepository
import com.lumisdinos.mindicador.ui.mapper.SerieViewMapper
import com.lumisdinos.mindicador.ui.model.SerieView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DetailViewModel @Inject constructor(
    private val serieLogicRepo: SerieLogicRepository,
    private val serieRepo: SerieRepository,
    private val serieStateRepo: SerieStateRepository,
    private val serieViewMapper: SerieViewMapper
) : ViewModel() {

    private var currCode = ""
    private val _series: MutableLiveData<List<SerieModel>> = MutableLiveData<List<SerieModel>>()
    val series: LiveData<List<SerieModel>> = _series

    private val currencyFlow = MutableStateFlow(currCode);
    val serieState = currencyFlow.flatMapLatest {
        serieStateRepo.getSerieStateFlow(currCode)
    }.catch {
        Timber.d("qwer getSerieStateFlow catch: %s", it.message)
    }
        .asLiveData(Dispatchers.IO);

    fun setNewCurrencyForState(newCurrency: String) {
        currCode = newCurrency
        currencyFlow.value = newCurrency
    }

    fun downloadSeriesByCurrencyCode(currencyCode: String = currCode) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                setNewCurrencyForState(currencyCode)
                _series.postValue(serieRepo.getSerieForMonth(currencyCode, true))
            }
        }
    }

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