package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.common.CurrencyOrder
import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import com.lumisdinos.mindicador.domain.repos.CurrencyLogicRepository
import com.lumisdinos.mindicador.domain.repos.CurrencyRepository
import com.lumisdinos.mindicador.domain.repos.CurrencyStateRepository
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

//    override fun filterByCodigoOrName(value: String) {
//        Timber.d("qwer filterByCodigoOrName")
//    }

    override suspend fun changeOrder(): List<CurrencyModel> {
        Timber.d("qwer changeOrder")
        val list = currencyRepo.getAllCurrencies(false)
        val newOrder = getNextOrder()
        Timber.d("qwer changeOrder newOrder: %s", newOrder)
        val sortedList = when (newOrder) {
            CurrencyOrder.ASCENDING.name -> {
                list.sortedBy { it.nombre }
            }
            CurrencyOrder.DESCENDING.name -> {
                list.sortedByDescending { it.nombre }
            }
            else -> {
                list
            }
        }
        newOrderSet(newOrder)
        return sortedList
    }

    override suspend fun messageIsShown() {
        Timber.d("qwer messageIsShown")
        currencyState = currencyState.copy(errorMessage = null)
        currencyStateRepo.insertCurrencyState(currencyState)
    }

    private fun getNextOrder(): String? {
        return when (currencyState.order) {
            null -> {
                CurrencyOrder.ASCENDING.name
            }
            CurrencyOrder.ASCENDING.name -> {
                CurrencyOrder.DESCENDING.name
            }
            else -> {
                null
            }
        }
    }

    private fun newOrderSet(newOrder: String?) {
        Timber.d("qwer newOrderSet: %s", newOrder)
        currencyState = currencyState.copy(order = newOrder)
        currencyStateRepo.insertCurrencyState(currencyState)
    }

}