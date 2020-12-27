package com.lumisdinos.mindicador.domain.model

data class SerieStateModel(
    val codigo: String = "",
    val updateTime: Long? = 0,
    val errorMessage: String? = null,
    val sharedMessage: String? = null
)