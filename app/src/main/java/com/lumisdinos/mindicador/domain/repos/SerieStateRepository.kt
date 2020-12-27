package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import kotlinx.coroutines.flow.Flow

interface SerieStateRepository {

    fun getSerieStateFlow(currencyCode: String): Flow<SerieStateModel?>

    fun getSerieState(currencyCode: String): SerieStateModel?

    fun insertSerieState(serieState: SerieStateModel)

    fun deleteSerieState(currencyCode: String)

    fun deleteAllSerieStates()
}