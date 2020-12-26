package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.common.ResourceState
import com.lumisdinos.mindicador.data.local.CurrencyDao
import com.lumisdinos.mindicador.data.local.CurrencyLocalSource
import com.lumisdinos.mindicador.data.mapper.CurrencyDataMapper
import com.lumisdinos.mindicador.data.remote.CurrencyRemoteSource
import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.repos.CurrencyRepository
import com.lumisdinos.mindicador.domain.repos.CurrencyStateRepository
import timber.log.Timber
import javax.inject.Inject

class CurrencyDataRepository @Inject constructor(
    private val currencyRemote: CurrencyRemoteSource,
    private val currencyLocal: CurrencyLocalSource,
    private val currencyDao: CurrencyDao,
    private val currencyStateRepo: CurrencyStateRepository,
    private val currencyMapper: CurrencyDataMapper
) : CurrencyRepository {

    override suspend fun getAllCurrencies(forceUpdate: Boolean): List<CurrencyModel> {
        Timber.d("qwer getAllCurrencies")
        if (forceUpdate) {
            val remoteResponse = currencyRemote.getAllCurrencies()
            val isRemoteError = remoteResponse.state == ResourceState.ERROR
            val list: MutableList<CurrencyModel> = mutableListOf()
            if (isRemoteError) {
                Timber.d("qwer getAllCurrencies isRemoteError: %s", remoteResponse.message)
                list.addAll(currencyLocal.getAllCurrencies()
                    .map { with(currencyMapper) { it.fromEntityToDomain() } })
                updateCurrencyState(remoteResponse.message)
            } else {

                remoteResponse.data?.let {
                    Timber.d("qwer getAllCurrencies remoteResponse.data: %s", it)
                    list.addAll(it.map { with(currencyMapper) { it.fromDataToDomain() } })
                    Timber.d("qwer getAllCurrencies list: %s", it)
                    insertAllCurrencies(list)
                }
            }

            return list
        } else {
            return currencyLocal.getAllCurrencies().map { with(currencyMapper) {it.fromEntityToDomain()} }
        }
    }

    private fun updateCurrencyState( message: String? = null) {
        Timber.d("qwer setStateCurrencies")
        var currencyState = currencyStateRepo.getCurrencyState()
        currencyState = currencyState.copy(
            errorMessage = message//,
            //isErrorMessageNotShown = !message.isNullOrEmpty()
        )
        currencyStateRepo.insertCurrencyState(currencyState)
    }

    override fun getCurrency(codigo: String): CurrencyModel? {
        return currencyLocal.getCurrency(codigo)?.let { with(currencyMapper) {it.fromEntityToDomain()} }
    }

    override fun insertAllCurrencies(currencies: List<CurrencyModel>) {
        val list = currencies.map { with(currencyMapper) { it.fromDomainToEntity() } }
        //currencyLocal.insertAllCurrencies(currencies.map { with(currencyMapper) { it.fromDomainToEntity() } })
        Timber.d("qwer insertAllCurrencies list: %s", list)
        currencyLocal.insertAllCurrencies(list)
    }

    override fun insertCurrency(currency: CurrencyModel) {
        currencyLocal.insertCurrency(currency.let { with(currencyMapper) { it.fromDomainToEntity() } })
    }

    override fun deleteCurrency(codigo: String) {
        currencyLocal.deleteCurrency(codigo)
    }

    override fun deleteAllCurrencies() {
        currencyLocal.deleteAllCurrencies()
    }

//    override fun getMaxIdCurrency(): Int {
//        return currencyLocal.getMaxIdCurrency()
//    }

    override fun getCurrencyCount(): Int {
        return currencyLocal.getCurrencyCount()
    }


}