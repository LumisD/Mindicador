package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import kotlinx.coroutines.flow.Flow

interface CurrencyLogicRepository {

//    fun getCurrencyStateFlow(): Flow<CurrencyStateModel>
//
//    fun getCurrenciesFlow(): Flow<List<CurrencyModel>>

    fun filterByCodigoOrName(value: String)

    fun orderCurrencies(isAscending: Boolean)

    fun messageIsShown()

}