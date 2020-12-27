package com.lumisdinos.mindicador.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lumisdinos.mindicador.data.Constants.CURRENCY_CODIGO
import com.lumisdinos.mindicador.data.Constants.CURRENCY_FECHA
import com.lumisdinos.mindicador.data.Constants.CURRENCY_ID
import com.lumisdinos.mindicador.data.Constants.CURRENCY_VALOR
import com.lumisdinos.mindicador.data.Constants.ID

@Entity(tableName = "serie")
data class SerieEntity @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = ID) val id: Int? = 0,
    @ColumnInfo(name = CURRENCY_CODIGO) val currencyCode: String? = "",
    @ColumnInfo(name = CURRENCY_FECHA) val fecha: String? = "",
    @ColumnInfo(name = CURRENCY_VALOR) val valor: Double? = 0.0
)