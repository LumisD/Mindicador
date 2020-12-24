package com.lumisdinos.mindicador.data.remote

import com.lumisdinos.mindicador.data.remote.model.CurrencyEntry
import timber.log.Timber
import javax.inject.Inject

class CurrencyRemoteSourceImpl @Inject constructor(
    private val restApi: CurrencyRestApi
) : CurrencyRemoteSource {

    //override suspend fun getAllCurrencies(): List<CurrencyEntry> = restApi.getAllCurrencies()
    override suspend fun getAllCurrencies(): List<CurrencyEntry>? {
        Timber.d("qwer getAllCurrencies")
        val remoteCurr = restApi.getAllCurrencies()
        return remoteCurr
    }

}