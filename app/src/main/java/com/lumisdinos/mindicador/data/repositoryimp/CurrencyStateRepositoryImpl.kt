package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.data.local.CurrencyDao
import com.lumisdinos.mindicador.data.mapper.CurrencyDataMapper
import com.lumisdinos.mindicador.data.mapper.CurrencyStateDataMapper
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import com.lumisdinos.mindicador.domain.repos.CurrencyStateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class CurrencyStateRepositoryImpl @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val currencyDataMapper: CurrencyDataMapper,
    private val currencyStateDataMapper: CurrencyStateDataMapper
) : CurrencyStateRepository {

    override fun getCurrencyStateFlow(): Flow<CurrencyStateModel> {
        Timber.d("qwer getCurrencyStateFlow")
        return currencyDao.getCurrencyStateFlow().map {
            if (it == null) {
                Timber.d("qwer getCurrencyState if (it == null) -> new CurrencyStateModel")
                CurrencyStateModel()
            } else {
                Timber.d("qwer getCurrencyState -> currencyDao.getAllCurrencies")
                //val currencies = currencyDao.getAllCurrencies().map { with(currencyDataMapper) {it.fromEntityToDomain()} }
                with(currencyStateDataMapper) { it.fromEntityToDomain() }
            }
        }
    }

    override fun getCurrencyState(): CurrencyStateModel {
        return currencyDao.getCurrencyState()?.let { with(currencyStateDataMapper) {it.fromEntityToDomain()} }
            ?: CurrencyStateModel()
    }

    override fun insertCurrencyState(currencyState: CurrencyStateModel)  =
        currencyDao.insertCurrencyState(with(currencyStateDataMapper) { currencyState.fromDomainToEntity() })

    override fun deleteAllCurrencyStates() = currencyDao.deleteAllCurrencyStates()

}