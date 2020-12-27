package com.lumisdinos.mindicador.data.mapper

import com.lumisdinos.mindicador.data.local.model.SerieStateEntity
import com.lumisdinos.mindicador.domain.model.SerieModel
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import javax.inject.Inject

class SerieStateDataMapper @Inject constructor() {

    fun SerieStateEntity.fromEntityToDomain() = SerieStateModel(
        codigo = codigo,
        updateTime = updateTime,
        errorMessage = errorMessage,
        sharedMessage = sharedMessage
    )

    fun SerieStateModel.fromDomainToEntity() = SerieStateEntity(
        codigo = codigo,
        updateTime = updateTime,
        errorMessage = errorMessage,
        sharedMessage = sharedMessage
    )

}