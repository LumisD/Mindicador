package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import kotlinx.coroutines.flow.Flow

interface CurrencyStateRepository {

    fun getCurrencyStateFlow(): Flow<CurrencyStateModel>

    fun getCurrencyState(): CurrencyStateModel

    fun insertCurrencyState(currencyState: CurrencyStateModel)

    fun deleteAllCurrencyStates()
}