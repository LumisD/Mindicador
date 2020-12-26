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
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val currencyLogicRepo: CurrencyLogicRepository,
    private val currencyRepo: CurrencyRepository,
    private val currencyStateRepo: CurrencyStateRepository,
    private val currencyViewMapper: CurrencyViewMapper
) : ViewModel() {

    @ExperimentalCoroutinesApi
    val currencyState: LiveData<CurrencyStateModel> = currencyStateRepo
        .getCurrencyStateFlow()
        .catch {
            Timber.d("qwer getCurrencyStateFlow catch: %s", it.message)
        }
        .asLiveData()

    @ExperimentalCoroutinesApi
    val currencies: LiveData<List<CurrencyModel>> = currencyRepo
        .getCurrenciesFlow()
        .catch {
            Timber.d("qwer getCurrenciesFlow catch: %s", it.message)
        }
        .asLiveData()

    fun downloadCurrencies() {
        Timber.d("qwer downloadCurrencies")
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                currencyRepo.getAllCurrencies(true)
            }
        }
    }

    fun filterByCodigoOrName(value: String) {
        currencyLogicRepo.filterByCodigoOrName(value)
    }

    fun orderCurrencies(isAscending: Boolean) {
        currencyLogicRepo.orderCurrencies(isAscending)
    }

    fun messageIsShown() {
        currencyLogicRepo.messageIsShown()
    }

    fun convertCurrencyModelsToCurrencyViews(currencyModels: List<CurrencyModel>): List<CurrencyView> {
        Timber.d("qwer convertCurrencyModelsToCurrencyViews currencyModels: %s", currencyModels)
        val list = currencyModels.map { with(currencyViewMapper) { it.fromDomainToView() } }
        Timber.d("qwer convertCurrencyModelsToCurrencyViews list: %s", list)
        return list
    }

}