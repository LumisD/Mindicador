package com.lumisdinos.mindicador.data.local

import com.lumisdinos.mindicador.data.local.model.CurrencyEntity

interface CurrencyLocalSource {

    suspend fun getAllCurrencies(): List<CurrencyEntity>

    suspend fun getCurrency(currencyId: Int): CurrencyEntity?

    suspend fun insertAllCurrencies(currencies: List<CurrencyEntity>)

    suspend fun insertCurrency(currency: CurrencyEntity)

    suspend fun deleteCurrency(currencyId: Int)

    suspend fun deleteAllCurrencies()

    suspend fun getMaxIdCurrency(): Int

    suspend fun getCurrencyCount(): Int

}