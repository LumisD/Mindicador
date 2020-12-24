package com.lumisdinos.mindicador.data.remote.model

import com.google.gson.annotations.SerializedName
import com.lumisdinos.mindicador.data.Constants.CURRENCY_CODIGO
import com.lumisdinos.mindicador.data.Constants.CURRENCY_FECHA
import com.lumisdinos.mindicador.data.Constants.CURRENCY_NOMBRE
import com.lumisdinos.mindicador.data.Constants.CURRENCY_UNIDAD_MEDIDA
import com.lumisdinos.mindicador.data.Constants.CURRENCY_VALOR
import com.lumisdinos.mindicador.data.Constants.ID

data class CurrencyEntry(
    //@SerializedName(ID) val id: Int?,
    @SerializedName(CURRENCY_CODIGO) val codigo: String?,
    @SerializedName(CURRENCY_NOMBRE) val nombre: String?,
    @SerializedName(CURRENCY_UNIDAD_MEDIDA) val unidadMedida: String?,
    @SerializedName(CURRENCY_FECHA) val fecha: String?,
    @SerializedName(CURRENCY_VALOR) val valor: Double?
)