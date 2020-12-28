package com.lumisdinos.mindicador.data.remote

import com.lumisdinos.mindicador.common.Resource
import com.lumisdinos.mindicador.data.remote.model.CurrencyEntry


interface CurrencyRemoteSource {

    suspend fun getAllCurrencies(): Resource<List<CurrencyEntry>>

}