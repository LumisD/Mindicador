package com.lumisdinos.mindicador.data.local

import com.lumisdinos.mindicador.data.local.model.CurrencyEntity
import javax.inject.Inject

class CurrencyLocalSourceImpl @Inject constructor(
    private val currencyDao: CurrencyDao
) : CurrencyLocalSource {

    override fun getAllCurrencies(): List<CurrencyEntity> {
        return currencyDao.getAllCurrencies()
    }

    override fun getCurrency(codigo: String): CurrencyEntity? {
        return currencyDao.getCurrency(codigo)
    }

    override fun insertAllCurrencies(currencies: List<CurrencyEntity>) {
        currencyDao.insertAllCurrencies(currencies)
    }

    override fun insertCurrency(currency: CurrencyEntity) {
        currencyDao.insertCurrency(currency)
    }

    override fun deleteCurrency(codigo: String) {
        currencyDao.deleteCurrency(codigo)
    }

    override fun deleteAllCurrencies() {
        currencyDao.deleteAllCurrencies()
    }

//    override fun getMaxIdCurrency(): Int {
//        return currencyDao.getMaxIdCurrency()
//    }

    override fun getCurrencyCount(): Int {
        return currencyDao.getCurrencyCount()
    }

}
