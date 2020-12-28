package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.common.CurrencyOrder
import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import com.lumisdinos.mindicador.domain.repos.CurrencyLogicRepository
import com.lumisdinos.mindicador.domain.repos.CurrencyRepository
import com.lumisdinos.mindicador.domain.repos.CurrencyStateRepository
import javax.inject.Inject

class CurrencyLogicRepositoryImpl @Inject constructor(
    private val currencyRepo: CurrencyRepository,
    private val currencyStateRepo: CurrencyStateRepository
) : CurrencyLogicRepository {

    override suspend fun changeOrder(): List<CurrencyModel> {
        val list = currencyRepo.getAllCurrencies(false)
        val currencyState = currencyStateRepo.getCurrencyState()
        val newOrder = getNextOrder(currencyState)
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
        newOrderSet(newOrder, currencyState)
        return sortedList
    }

    override suspend fun messageIsShown() {
        var currencyState = currencyStateRepo.getCurrencyState()
        currencyState = currencyState.copy(errorMessage = null)
        currencyStateRepo.insertCurrencyState(currencyState)
    }

    override suspend fun setLoading(isLoading: Boolean) {
        var currencyState = currencyStateRepo.getCurrencyState()
        currencyState = currencyState.copy(
            loading = isLoading
        )
        currencyStateRepo.insertCurrencyState(currencyState)
    }

    private fun getNextOrder(currencyState: CurrencyStateModel): String? {
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

    private fun newOrderSet(newOrder: String?, currencySt: CurrencyStateModel) {
        val currencyState = currencySt.copy(order = newOrder)
        currencyStateRepo.insertCurrencyState(currencyState)
    }

}