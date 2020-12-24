package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.SerieStateModel
import kotlinx.coroutines.flow.Flow

interface DetailsLogicRepository {

    fun getSerieState(currencyId: Int): Flow<SerieStateModel>

    fun downloadSeries(currencyId: Int)

    fun share()

    fun seriesAreRendered()

}