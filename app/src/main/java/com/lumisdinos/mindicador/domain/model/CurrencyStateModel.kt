package com.lumisdinos.mindicador.domain.model

data class CurrencyStateModel(
    val errorMessage: String? = null,
    val order: String? = null,
    val loading: Boolean = false
)