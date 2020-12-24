package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import kotlinx.coroutines.flow.Flow

interface CurrencyLogicRepository {

    fun getCurrencyState(): Flow<CurrencyStateModel>

    fun downloadCurrencies()

    fun filterByCodigoOrName(value: String)

    fun orderCurrencies(isAscending: Boolean)

    fun currenciesAreRendered()

}