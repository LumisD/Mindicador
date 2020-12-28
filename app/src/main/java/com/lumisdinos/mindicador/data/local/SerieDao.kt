package com.lumisdinos.mindicador.data.local

import androidx.room.*
import com.lumisdinos.mindicador.data.local.model.SerieEntity
import com.lumisdinos.mindicador.data.local.model.SerieStateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SerieDao {

    //SerieState

    @Query("SELECT * FROM serie_state WHERE codigo = :currencyCode")
    fun getSerieStateFlow(currencyCode: String): Flow<SerieStateEntity?>

    @Query("SELECT * FROM serie_state WHERE codigo = :currencyCode")
    fun getSerieState(currencyCode: String): SerieStateEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSerieState(serieState: SerieStateEntity)

    //Serie

    @Query("SELECT * FROM serie WHERE codigo = :currencyCode")
    fun getSeriesByCurrencyCode(currencyCode: String): List<SerieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSeries(series: List<SerieEntity>)

    @Query("DELETE FROM serie WHERE codigo = :currencyCode")
    fun deleteSeriesByCurrencyCode(currencyCode: String)
}