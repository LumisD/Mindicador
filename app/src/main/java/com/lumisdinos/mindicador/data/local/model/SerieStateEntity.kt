package com.lumisdinos.mindicador.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "serie_state")
data class SerieStateEntity @JvmOverloads constructor(
    //@PrimaryKey @ColumnInfo(name = ID) val id: Int? = 0,
    @ColumnInfo(name = "is_series_updated") val isSeriesUpdated: Boolean = false,
    @ColumnInfo(name = "update_time") val updateTime: Long? = 0L,
    @ColumnInfo(name = "error_message") val errorMessage: String? = ""
)