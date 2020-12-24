package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import com.lumisdinos.mindicador.domain.repos.CurrencyLogicRepository
import com.lumisdinos.mindicador.domain.repos.CurrencyRepository
import com.lumisdinos.mindicador.domain.repos.CurrencyStateRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class CurrencyLogicRepositoryImpl @Inject constructor(
    private val currencyRepo: CurrencyRepository,
    private val currencyStateRepo: CurrencyStateRepository
) : CurrencyLogicRepository {

    var currencyState = CurrencyStateModel()
    private var currency: CurrencyModel? = null


    override fun getCurrencyState(): Flow<CurrencyStateModel> {
        Timber.d("qwer getCurrencyState")
        val state = currencyStateRepo.getCurrencyState()
        return state
    }


    override fun downloadCurrencies() {
        Timber.d("qwer downloadCurrencies")
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                currencyRepo.getAllCurrencies()
            }
        }
    }


    override fun filterByCodigoOrName(value: String) {
        Timber.d("qwer filterByCodigoOrName")
    }

    override fun orderCurrencies(isAscending: Boolean) {
        Timber.d("qwer orderCurrencies")
    }


    override fun currenciesAreRendered() {
        Timber.d("qwer currenciesAreRendered")
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) { setStateCurrencies(currencyState.currencies, false) }
        }
    }

    private suspend fun setStateCurrencies(currencies: List<CurrencyModel>, isCurrenciesNew: Boolean = true) {
        Timber.d("qwer setStateCurrencies")
        withContext(Dispatchers.IO) {
            currencyState = currencyState.copy(currencies = currencies, isCurrenciesUpdated = isCurrenciesNew)
            currencyStateRepo.insertCurrencyState(currencyState)
        }
    }

}