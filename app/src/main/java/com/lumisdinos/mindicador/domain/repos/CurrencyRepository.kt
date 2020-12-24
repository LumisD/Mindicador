package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.CurrencyModel

interface CurrencyRepository {

    suspend fun getAllCurrencies(): List<CurrencyModel>

    suspend fun getCurrency(currencyId: Int)//: CurrencyModel?

    suspend fun insertAllCurrencies(currencies: List<CurrencyModel>)

    suspend fun insertCurrency(currency: CurrencyModel)

    suspend fun deleteCurrency(currencyId: Int)

    suspend fun deleteAllCurrencies()

    suspend fun getMaxIdCurrency(): Int

    suspend fun getCurrencyCount(): Int
}