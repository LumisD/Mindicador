package com.lumisdinos.mindicador.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lumisdinos.mindicador.data.Constants
import com.lumisdinos.mindicador.data.Constants.ID

@Entity(tableName = "serie_state")
data class SerieStateEntity @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = ID) val id: Int? = 0,
    @ColumnInfo(name = Constants.CURRENCY_CODIGO) val codigo: String = "",
    @ColumnInfo(name = "update_time") val updateTime: Long? = 0L,
    @ColumnInfo(name = "error_message") val errorMessage: String? = ""
)