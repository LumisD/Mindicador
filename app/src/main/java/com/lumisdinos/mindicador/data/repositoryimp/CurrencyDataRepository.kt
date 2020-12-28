package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.common.ResourceState
import com.lumisdinos.mindicador.data.local.CurrencyDao
import com.lumisdinos.mindicador.data.local.CurrencyLocalSource
import com.lumisdinos.mindicador.data.mapper.CurrencyDataMapper
import com.lumisdinos.mindicador.data.remote.CurrencyRemoteSource
import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.repos.CurrencyRepository
import com.lumisdinos.mindicador.domain.repos.CurrencyStateRepository
import javax.inject.Inject

class CurrencyDataRepository @Inject constructor(
    private val currencyRemote: CurrencyRemoteSource,
    private val currencyLocal: CurrencyLocalSource,
    private val currencyStateRepo: CurrencyStateRepository,
    private val currencyMapper: CurrencyDataMapper
) : CurrencyRepository {

    override suspend fun getAllCurrencies(forceUpdate: Boolean): List<CurrencyModel> {
        if (forceUpdate) {
            val remoteResponse = currencyRemote.getAllCurrencies()
            val isRemoteError = remoteResponse.state == ResourceState.ERROR
            val list: MutableList<CurrencyModel> = mutableListOf()
            if (isRemoteError) {
                list.addAll(currencyLocal.getAllCurrencies()
                    .map { with(currencyMapper) { it.fromEntityToDomain() } })
                setMessageInState(remoteResponse.message)
            } else {

                remoteResponse.data?.let {
                    list.addAll(it.map { with(currencyMapper) { it.fromDataToDomain() } })
                    insertAllCurrencies(list)
                }
            }

            return list
        } else {
            return currencyLocal.getAllCurrencies().map { with(currencyMapper) {it.fromEntityToDomain()} }
        }
    }

    override fun getCurrency(codigo: String): CurrencyModel? {
        return currencyLocal.getCurrency(codigo)?.let { with(currencyMapper) {it.fromEntityToDomain()} }
    }

    override fun insertAllCurrencies(currencies: List<CurrencyModel>) {
        val list = currencies.map { with(currencyMapper) { it.fromDomainToEntity() } }
        currencyLocal.insertAllCurrencies(list)
    }

    private fun setMessageInState(message: String? = null) {
        var currencyState = currencyStateRepo.getCurrencyState()
        currencyState = currencyState.copy(
            errorMessage = message
        )
        currencyStateRepo.insertCurrencyState(currencyState)
    }

}