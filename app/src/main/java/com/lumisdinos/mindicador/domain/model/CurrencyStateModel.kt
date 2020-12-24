package com.lumisdinos.mindicador.domain.model

data class CurrencyStateModel(
    val currencies: List<CurrencyModel> = emptyList(),
    val isCurrenciesUpdated: Boolean = false,
    val updateTime: Long? = 0,
    val errorMessage: String? = ""
)