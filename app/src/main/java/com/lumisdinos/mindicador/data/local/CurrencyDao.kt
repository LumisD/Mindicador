package com.lumisdinos.mindicador.data.local

import androidx.room.*
import com.lumisdinos.mindicador.data.local.model.CurrencyEntity
import com.lumisdinos.mindicador.data.local.model.CurrencyStateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    //CurrencyState

    @Query("SELECT * FROM currency_state")
    fun getCurrencyStateFlow(): Flow<CurrencyStateEntity?>

    @Query("SELECT * FROM currency_state")
    fun getCurrencyState(): CurrencyStateEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencyState(currencyState: CurrencyStateEntity)

    //Currency

    @Query("SELECT * FROM currency")
    fun getAllCurrencies(): List<CurrencyEntity>

    @Query("SELECT * FROM currency WHERE codigo = :codigo LIMIT 1")
    fun getCurrency(codigo: String): CurrencyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCurrencies(currencies: List<CurrencyEntity>)
}