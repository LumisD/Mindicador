package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import kotlinx.coroutines.flow.Flow

interface CurrencyStateRepository {

    fun getCurrencyState(): Flow<CurrencyStateModel>

    fun insertCurrencyState(currencyState: CurrencyStateModel)

    fun deleteAllCurrencyStates()
}