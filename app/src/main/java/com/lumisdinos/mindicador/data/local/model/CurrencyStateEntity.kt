package com.lumisdinos.mindicador.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lumisdinos.mindicador.data.Constants.ID

@Entity(tableName = "currency_state")
data class CurrencyStateEntity @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = ID) val id: Int? = 0,
    @ColumnInfo(name = "update_time") val updateTime: Long? = 0L,
    @ColumnInfo(name = "error_message") val errorMessage: String? = null,
    @ColumnInfo(name = "order") val order: String? = null
)