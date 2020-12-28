package com.lumisdinos.mindicador.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import com.lumisdinos.mindicador.domain.repos.CurrencyLogicRepository
import com.lumisdinos.mindicador.domain.repos.CurrencyRepository
import com.lumisdinos.mindicador.domain.repos.CurrencyStateRepository
import com.lumisdinos.mindicador.ui.mapper.CurrencyViewMapper
import com.lumisdinos.mindicador.ui.model.CurrencyView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HomeViewModel @Inject constructor(
    private val currencyLogicRepo: CurrencyLogicRepository,
    private val currencyRepo: CurrencyRepository,
    private val currencyStateRepo: CurrencyStateRepository,
    private val currencyViewMapper: CurrencyViewMapper
) : ViewModel() {

    val currencyState: LiveData<CurrencyStateModel?> = currencyStateRepo
        .getCurrencyStateFlow()
        .catch {
        }
        .asLiveData()

    private val _currencies: MutableLiveData<List<CurrencyModel>> = MutableLiveData<List<CurrencyModel>>()
    val currencies: LiveData<List<CurrencyModel>> = _currencies

    fun downloadCurrencies() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                currencyLogicRepo.setLoading(true)
                val list = currencyRepo.getAllCurrencies(true)
                _currencies.postValue(list)
                currencyLogicRepo.setLoading(false)
            }
        }
    }

    fun changeOrder() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                _currencies.postValue(currencyLogicRepo.changeOrder())
            }
        }
    }

    fun messageIsShown() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                currencyLogicRepo.messageIsShown()
            }
        }
    }

    fun convertCurrencyModelsToCurrencyViews(currencyModels: List<CurrencyModel>): List<CurrencyView> {
        val list = currencyModels.map { with(currencyViewMapper) { it.fromDomainToView() } }
        return list
    }

}