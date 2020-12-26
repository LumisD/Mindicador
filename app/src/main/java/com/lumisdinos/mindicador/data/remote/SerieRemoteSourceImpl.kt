package com.lumisdinos.mindicador.data.remote

import com.lumisdinos.mindicador.common.util.numbToStr
import com.lumisdinos.mindicador.data.remote.model.SerieEntry
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class SerieRemoteSourceImpl @Inject constructor(
    private val restApi: CurrencyRestApi
) : SerieRemoteSource {

    //override suspend fun getSeriesByCurrencyId(currencyId: Int): List<SerieEntry>? = restApi.getAllCurrencies()
    override suspend fun getSeriesByCurrencyId(currencyId: Int): List<SerieEntry>? {//Response<List<SerieEntry>>?
        Timber.d("qwer getSeriesByCurrencyId")
        val remoteSeries = restApi.getCurrencyForMonth(numbToStr(currencyId))
        return remoteSeries
    }

}