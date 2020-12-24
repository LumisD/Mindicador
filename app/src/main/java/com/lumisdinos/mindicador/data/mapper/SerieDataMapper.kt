package com.lumisdinos.mindicador.data.mapper

import com.lumisdinos.mindicador.data.local.model.SerieEntity
import com.lumisdinos.mindicador.data.remote.model.SerieEntry
import com.lumisdinos.mindicador.domain.model.SerieModel
import javax.inject.Inject

class SerieDataMapper @Inject constructor() {

    fun SerieEntry.fromDataToDomain() = SerieModel(
        //todo: pass id and currency id?
        id = null,
        currencyId = null,
        fecha = fecha,
        valor = valor
    )

    fun SerieEntry.fromDataToEntity() = SerieEntity(
        //todo: pass id and currency id?
        fecha = fecha,
        valor = valor
    )

    fun SerieEntity.fromEntityToDomain() = SerieModel(
        id = id,
        currencyId = currencyId,
        fecha = fecha,
        valor = valor
    )

    fun SerieModel.fromDomainToEntity() = SerieEntity(
        id = id,
        currencyId = currencyId,
        fecha = fecha,
        valor = valor
    )

}