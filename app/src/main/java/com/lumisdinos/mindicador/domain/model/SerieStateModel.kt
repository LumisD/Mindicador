package com.lumisdinos.mindicador.domain.model

data class SerieStateModel(
    val codigo: String = "",
    val errorMessage: String? = null,
    val sharedMessage: String? = null,
    val loading: Boolean = false
)