package com.lumisdinos.mindicador.data.mapper

import com.lumisdinos.mindicador.data.local.model.SerieEntity
import com.lumisdinos.mindicador.data.remote.model.SerieEntry
import com.lumisdinos.mindicador.domain.model.SerieModel
import javax.inject.Inject

class SerieDataMapper @Inject constructor() {

    fun SerieEntry.fromDataToDomain(currencyCode: String) = SerieModel(
        id = null,
        currencyCode = currencyCode,
        fecha = fecha,
        valor = valor
    )

    fun SerieEntity.fromEntityToDomain() = SerieModel(
        id = id,
        currencyCode = currencyCode,
        fecha = fecha,
        valor = valor
    )

    fun SerieModel.fromDomainToEntity() = SerieEntity(
        id = id,
        currencyCode = currencyCode,
        fecha = fecha,
        valor = valor
    )

}