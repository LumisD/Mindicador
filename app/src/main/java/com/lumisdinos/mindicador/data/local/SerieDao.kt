package com.lumisdinos.mindicador.data.local

import androidx.room.*
import com.lumisdinos.mindicador.data.local.model.SerieEntity
import com.lumisdinos.mindicador.data.local.model.SerieStateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SerieDao {

    //SerieState

    @Query("SELECT * FROM serie_state")
    fun getSerieState(): Flow<SerieStateEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSerieState(serieState: SerieStateEntity)

    @Query("DELETE FROM serie_state")
    fun deleteAllSerieStates()

    //Serie

    @Query("SELECT * FROM serie WHERE currency_id = :currencyId")
    fun getSeriesByCurrencyId(currencyId: Int): List<SerieEntity>

    @Query("SELECT * FROM serie WHERE id = :serieId LIMIT 1")
    fun getSerie(serieId: Int): SerieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSeries(series: List<SerieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSerie(serie: SerieEntity)

    @Query("DELETE FROM serie WHERE id = :serieId")
    fun deleteSerie(serieId: Int)

    @Query("DELETE FROM serie WHERE currency_id = :currencyId")
    fun deleteSeriesByCurrencyId(currencyId: Int)

    @Query("DELETE FROM serie")
    fun deleteAllSeries()

    @Query("SELECT MAX(id) FROM serie")
    fun getMaxIdSerie(): Int

    @Query("SELECT COUNT(*) FROM serie")
    fun getSerieCount(): Int
}