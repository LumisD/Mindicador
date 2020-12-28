package com.lumisdinos.mindicador.domain.model

data class CurrencyModel(
    val codigo: String,
    val nombre: String?,
    val unidadMedida: String?,
    val fecha: String?,
    val valor: Double?
)