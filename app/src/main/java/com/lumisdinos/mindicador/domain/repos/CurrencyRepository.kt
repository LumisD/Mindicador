package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.CurrencyModel

interface CurrencyRepository {

    suspend fun getAllCurrencies(forceUpdate: Boolean): List<CurrencyModel>

    fun getCurrency(codigo: String): CurrencyModel?

    fun insertAllCurrencies(currencies: List<CurrencyModel>)

    fun insertCurrency(currency: CurrencyModel)

    fun deleteCurrency(codigo: String)

    fun deleteAllCurrencies()

    fun getCurrencyCount(): Int
}