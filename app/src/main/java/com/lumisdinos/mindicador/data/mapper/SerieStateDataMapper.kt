package com.lumisdinos.mindicador.data.mapper

import com.lumisdinos.mindicador.data.local.model.SerieStateEntity
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import javax.inject.Inject

class SerieStateDataMapper @Inject constructor() {

    fun SerieStateEntity.fromEntityToDomain() = SerieStateModel(
        codigo = codigo,
        errorMessage = errorMessage,
        sharedMessage = sharedMessage,
        loading = loading
    )

    fun SerieStateModel.fromDomainToEntity() = SerieStateEntity(
        codigo = codigo,
        errorMessage = errorMessage,
        sharedMessage = sharedMessage,
        loading = loading
    )

}