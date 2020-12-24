package com.lumisdinos.mindicador.data.local

import com.lumisdinos.mindicador.data.local.model.CurrencyEntity
import javax.inject.Inject

class CurrencyLocalSourceRepositoryImpl @Inject constructor(
    private val currencyDao: CurrencyDao
) : CurrencyLocalSource {

    override suspend  fun getAllCurrencies(): List<CurrencyEntity> {
        return currencyDao.getAllCurrencies()
    }

    override suspend  fun getCurrency(currencyId: Int): CurrencyEntity? {
        return currencyDao.getCurrency(currencyId)
    }

    override suspend  fun insertAllCurrencies(currencies: List<CurrencyEntity>) {
        currencyDao.insertAllCurrencies(currencies)
    }

    override suspend  fun insertCurrency(currency: CurrencyEntity) {
        currencyDao.insertCurrency(currency)
    }

    override suspend  fun deleteCurrency(currencyId: Int) {
        currencyDao.deleteCurrency(currencyId)
    }

    override suspend  fun deleteAllCurrencies() {
        currencyDao.deleteAllCurrencies()
    }

    override suspend  fun getMaxIdCurrency(): Int {
        return currencyDao.getMaxIdCurrency()
    }

    override suspend  fun getCurrencyCount(): Int {
        return currencyDao.getCurrencyCount()
    }

}
