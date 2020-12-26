package com.lumisdinos.mindicador.data.remote.model

import com.google.gson.annotations.SerializedName
import com.lumisdinos.mindicador.data.Constants.CURRENCY_CODIGO
import com.lumisdinos.mindicador.data.Constants.CURRENCY_FECHA
import com.lumisdinos.mindicador.data.Constants.CURRENCY_NOMBRE
import com.lumisdinos.mindicador.data.Constants.CURRENCY_UNIDAD_MEDIDA
import com.lumisdinos.mindicador.data.Constants.CURRENCY_VALOR
import com.lumisdinos.mindicador.data.Constants.ID

data class IndicadorEntry(
    @SerializedName("version") val version: String?,
    @SerializedName("autor") val autor: String?,
    @SerializedName("fecha") val fecha: String?,

    @SerializedName("uf") val uf: CurrencyEntry?,
    @SerializedName("ivp") val ivp: CurrencyEntry?,
    @SerializedName("dolar") val dolar: CurrencyEntry?,
    @SerializedName("dolar_intercambio") val dolar_intercambio: CurrencyEntry?,
    @SerializedName("euro") val euro: CurrencyEntry?,
    @SerializedName("ipc") val ipc: CurrencyEntry?,
    @SerializedName("utm") val utm: CurrencyEntry?,
    @SerializedName("imacec") val imacec: CurrencyEntry?,
    @SerializedName("tpm") val tpm: CurrencyEntry?,
    @SerializedName("libra_cobre") val libra_cobre: CurrencyEntry?,
    @SerializedName("tasa_desempleo") val tasa_desempleo: CurrencyEntry?,
    @SerializedName("bitcoin") val bitcoin: CurrencyEntry?
)