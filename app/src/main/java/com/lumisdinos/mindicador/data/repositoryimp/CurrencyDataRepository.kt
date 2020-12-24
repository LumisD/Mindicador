package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.data.local.CurrencyLocalSource
import com.lumisdinos.mindicador.data.mapper.CurrencyDataMapper
import com.lumisdinos.mindicador.data.remote.CurrencyRemoteSource
import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.repos.CurrencyRepository
import timber.log.Timber
import javax.inject.Inject

class CurrencyDataRepository @Inject constructor(
    private val currencyRemote: CurrencyRemoteSource,
    private val currencyLocal: CurrencyLocalSource,
    private val currencyMapper: CurrencyDataMapper
) : CurrencyRepository {

    override suspend fun getAllCurrencies(): List<CurrencyModel> {
        Timber.d("qwer getAllCurrencies")
        val remoteCurrencies = currencyRemote.getAllCurrencies()
        if (remoteCurrencies == null) {
            //todo: show message?
            return currencyLocal.getAllCurrencies()
                .map { with(currencyMapper) { it.fromEntityToDomain() } }
        } else {
            return remoteCurrencies.map { with(currencyMapper) { it.fromDataToDomain() } }//todo: pass id?
            //todo: save in db
        }
    }

    override suspend fun getCurrency(currencyId: Int) {
        currencyLocal.getCurrency(currencyId)
    }

    override suspend fun insertAllCurrencies(currencies: List<CurrencyModel>) {
        currencyLocal.insertAllCurrencies(currencies.map { with(currencyMapper) { it.fromDomainToEntity() } })
    }

    override suspend fun insertCurrency(currency: CurrencyModel) {
        currencyLocal.insertCurrency(currency.let { with(currencyMapper) { it.fromDomainToEntity() } })
    }

    override suspend fun deleteCurrency(currencyId: Int) {
        currencyLocal.deleteCurrency(currencyId)
    }

    override suspend fun deleteAllCurrencies() {
        currencyLocal.deleteAllCurrencies()
    }

    override suspend fun getMaxIdCurrency(): Int {
        return currencyLocal.getMaxIdCurrency()
    }

    override suspend fun getCurrencyCount(): Int {
        return currencyLocal.getCurrencyCount()
    }


}