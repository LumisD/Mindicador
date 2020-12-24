package com.lumisdinos.mindicador.data.mapper

import com.lumisdinos.mindicador.data.local.model.SerieStateEntity
import com.lumisdinos.mindicador.domain.model.SerieModel
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import javax.inject.Inject

class SerieStateDataMapper @Inject constructor() {

    fun SerieStateEntity.fromEntityToDomain(series: List<SerieModel>) = SerieStateModel(
        series = series,
        isSeriesUpdated = isSeriesUpdated,
        updateTime = updateTime,
        errorMessage = errorMessage
    )

    fun SerieStateModel.fromDomainToEntity() = SerieStateEntity(
        isSeriesUpdated = isSeriesUpdated,
        updateTime = updateTime,
        errorMessage = errorMessage
    )

}