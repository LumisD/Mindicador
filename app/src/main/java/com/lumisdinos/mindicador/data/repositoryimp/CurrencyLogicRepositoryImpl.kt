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

//    override fun getCurrencyStateFlow(): Flow<CurrencyStateModel> {
//        Timber.d("qwer getCurrencyStateFlow")
//        val state = currencyStateRepo.getCurrencyStateFlow()
//        return state
//    }
//
//    override fun getCurrenciesFlow(): Flow<List<CurrencyModel>> {
//        Timber.d("qwer getCurrenciesFlow")
//        return currencyRepo.getCurrenciesFlow()
//    }

    override fun filterByCodigoOrName(value: String) {
        Timber.d("qwer filterByCodigoOrName")
    }

    override fun orderCurrencies(isAscending: Boolean) {
        Timber.d("qwer orderCurrencies")
    }

    override fun messageIsShown() {
        Timber.d("qwer messageIsShown")
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                currencyState = currencyState.copy(errorMessage = null)
                currencyStateRepo.insertCurrencyState(currencyState)
            }
        }
    }

}