package com.lumisdinos.mindicador.data.local

import com.lumisdinos.mindicador.data.local.model.CurrencyEntity

interface CurrencyLocalSource {

    fun getAllCurrencies(): List<CurrencyEntity>

    fun getCurrency(codigo: String): CurrencyEntity?

    fun insertAllCurrencies(currencies: List<CurrencyEntity>)
}