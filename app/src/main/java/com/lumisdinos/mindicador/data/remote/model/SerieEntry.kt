package com.lumisdinos.mindicador.data.remote.model

import com.google.gson.annotations.SerializedName
import com.lumisdinos.mindicador.data.Constants.CURRENCY_FECHA
import com.lumisdinos.mindicador.data.Constants.CURRENCY_ID
import com.lumisdinos.mindicador.data.Constants.CURRENCY_VALOR
import com.lumisdinos.mindicador.data.Constants.ID

data class SerieEntry(
    @SerializedName(CURRENCY_FECHA) val fecha: String?,
    @SerializedName(CURRENCY_VALOR) val valor: Double?
)