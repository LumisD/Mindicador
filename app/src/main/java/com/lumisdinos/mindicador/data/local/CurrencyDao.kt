package com.lumisdinos.mindicador.data.local

import androidx.room.*
import com.lumisdinos.mindicador.data.local.model.CurrencyEntity
import com.lumisdinos.mindicador.data.local.model.CurrencyStateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    //CurrencyState

    @Query("SELECT * FROM currency_state")
    fun getCurrencyState(): Flow<CurrencyStateEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencyState(currencyState: CurrencyStateEntity)

    @Query("DELETE FROM currency_state")
    fun deleteAllCurrencyStates()

    //Currency

    @Query("SELECT * FROM currency")
    fun getAllCurrencies(): List<CurrencyEntity>

    @Query("SELECT * FROM currency WHERE id = :currencyId LIMIT 1")
    fun getCurrency(currencyId: Int): CurrencyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCurrencies(currencies: List<CurrencyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrency(currency: CurrencyEntity)

    @Query("DELETE FROM currency WHERE id = :currencyId")
    fun deleteCurrency(currencyId: Int)

    @Query("DELETE FROM currency")
    fun deleteAllCurrencies()

    @Query("SELECT MAX(id) FROM currency")
    fun getMaxIdCurrency(): Int

    @Query("SELECT COUNT(*) FROM currency")
    fun getCurrencyCount(): Int

}