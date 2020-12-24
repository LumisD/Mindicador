package com.lumisdinos.mindicador.data.remote

import com.lumisdinos.mindicador.data.remote.model.CurrencyEntry


interface CurrencyRemoteSource {

    suspend fun getAllCurrencies(): List<CurrencyEntry>?

}