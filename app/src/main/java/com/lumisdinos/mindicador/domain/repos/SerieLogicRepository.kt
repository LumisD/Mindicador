package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.SerieStateModel
import kotlinx.coroutines.flow.Flow

interface SerieLogicRepository {

    fun getSerieState(currencyId: Int): Flow<SerieStateModel>

    fun downloadSeriesByCurrencyId(currencyId: Int)

    fun share()

    fun seriesAreRendered()

}