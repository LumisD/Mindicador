package com.lumisdinos.mindicador.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lumisdinos.mindicador.data.Constants.CURRENCY_CODIGO
import com.lumisdinos.mindicador.data.Constants.CURRENCY_FECHA
import com.lumisdinos.mindicador.data.Constants.CURRENCY_NOMBRE
import com.lumisdinos.mindicador.data.Constants.CURRENCY_UNIDAD_MEDIDA
import com.lumisdinos.mindicador.data.Constants.CURRENCY_VALOR
import com.lumisdinos.mindicador.data.Constants.ID

@Entity(tableName = "currency")
data class CurrencyEntity @JvmOverloads constructor(
    //@PrimaryKey @ColumnInfo(name = ID) val id: Int? = 0,
    @PrimaryKey @ColumnInfo(name = CURRENCY_CODIGO) val codigo: String = "",
    @ColumnInfo(name = CURRENCY_NOMBRE) val nombre: String? = "",
    @ColumnInfo(name = CURRENCY_UNIDAD_MEDIDA) val unidadMedida: String? = "",
    @ColumnInfo(name = CURRENCY_FECHA) val fecha: String? = "",
    @ColumnInfo(name = CURRENCY_VALOR) val valor: Double? = 0.0
)