package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.SerieStateModel
import kotlinx.coroutines.flow.Flow

interface SerieStateRepository {

    fun getSerieState(currencyId: Int): Flow<SerieStateModel>

    fun insertSerieState(serieState: SerieStateModel)

    fun deleteAllSerieStates()
}