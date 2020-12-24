package com.lumisdinos.mindicador.domain.model

data class SerieStateModel(
    val series: List<SerieModel> = emptyList(),
    val isSeriesUpdated: Boolean = false,
    val updateTime: Long? = 0,
    val errorMessage: String? = ""
)