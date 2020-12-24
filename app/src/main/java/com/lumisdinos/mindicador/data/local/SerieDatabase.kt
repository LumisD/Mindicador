package com.lumisdinos.mindicador.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lumisdinos.mindicador.data.local.model.SerieEntity
import com.lumisdinos.mindicador.data.local.model.SerieStateEntity

@Database(
    entities = [
        SerieStateEntity::class,
        SerieEntity::class
    ], version = 1, exportSchema = false
)
abstract class SerieDatabase : RoomDatabase() {

    abstract fun serieDao(): SerieDao

}