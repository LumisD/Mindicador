package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    fun getCurrenciesFlow(): Flow<List<CurrencyModel>>

    suspend fun getAllCurrencies(forceUpdate: Boolean): List<CurrencyModel>

    fun getCurrency(codigo: String): CurrencyModel?

    fun insertAllCurrencies(currencies: List<CurrencyModel>)

    fun insertCurrency(currency: CurrencyModel)

    fun deleteCurrency(codigo: String)

    fun deleteAllCurrencies()

    //fun getMaxIdCurrency(): Int

    fun getCurrencyCount(): Int
}