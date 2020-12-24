package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.data.local.CurrencyDao
import com.lumisdinos.mindicador.data.mapper.CurrencyDataMapper
import com.lumisdinos.mindicador.data.mapper.CurrencyStateDataMapper
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import com.lumisdinos.mindicador.domain.repos.CurrencyStateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyStateRepositoryImpl @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val currencyDataMapper: CurrencyDataMapper,
    private val currencyStateDataMapper: CurrencyStateDataMapper
) : CurrencyStateRepository {

    override fun getCurrencyState(): Flow<CurrencyStateModel> {
        return currencyDao.getCurrencyState().map {
            if (it == null) {
                CurrencyStateModel()
            } else {
                val currencies = currencyDao.getAllCurrencies().map { with(currencyDataMapper) {it.fromEntityToDomain()} }
                with(currencyStateDataMapper) { it.fromEntityToDomain(currencies) }
            }
        }
    }

    override fun insertCurrencyState(currencyState: CurrencyStateModel)  =
        currencyDao.insertCurrencyState(with(currencyStateDataMapper) { currencyState.fromDomainToEntity() })

    override fun deleteAllCurrencyStates() = currencyDao.deleteAllCurrencyStates()

}