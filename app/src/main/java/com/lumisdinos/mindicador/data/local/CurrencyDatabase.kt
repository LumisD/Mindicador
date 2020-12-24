package com.lumisdinos.mindicador.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lumisdinos.mindicador.data.local.model.CurrencyEntity
import com.lumisdinos.mindicador.data.local.model.CurrencyStateEntity

@Database(
    entities = [
        CurrencyStateEntity::class,
        CurrencyEntity::class
    ], version = 1, exportSchema = false
)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

}