package com.lumisdinos.mindicador.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import com.lumisdinos.mindicador.domain.repos.CurrencyLogicRepository
import com.lumisdinos.mindicador.ui.mapper.CurrencyViewMapper
import com.lumisdinos.mindicador.ui.model.CurrencyView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val currencyLogicRepo: CurrencyLogicRepository,
    private val currencyViewMapper: CurrencyViewMapper
) : ViewModel() {

    @ExperimentalCoroutinesApi
    val currencyState: LiveData<CurrencyStateModel> = currencyLogicRepo
        .getCurrencyState()
        .catch {
            Timber.d("qwer getCurrencyState catch: %s", it.message)
        }
        .asLiveData()

    fun downloadCurrencies() {
        currencyLogicRepo.downloadCurrencies()
    }

    fun filterByCodigoOrName(value: String) {
        currencyLogicRepo.filterByCodigoOrName(value)
    }

    fun orderCurrencies(isAscending: Boolean) {
        currencyLogicRepo.orderCurrencies(isAscending)
    }

    fun currenciesAreRendered() {
        currencyLogicRepo.currenciesAreRendered()
    }

    fun convertCurrencyModelsToCurrencyViews(currencyModels: List<CurrencyModel>): List<CurrencyView> {
        return currencyModels.map { with(currencyViewMapper) { it.fromDomainToView() } }
    }

}