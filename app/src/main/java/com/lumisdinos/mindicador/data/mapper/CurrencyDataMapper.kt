package com.lumisdinos.mindicador.data.mapper

import com.lumisdinos.mindicador.data.local.model.CurrencyEntity
import com.lumisdinos.mindicador.data.remote.model.CurrencyEntry
import com.lumisdinos.mindicador.domain.model.CurrencyModel
import javax.inject.Inject

class CurrencyDataMapper @Inject constructor() {

    fun CurrencyEntry.fromDataToDomain() = CurrencyModel(
        codigo = codigo,
        nombre = nombre,
        unidadMedida = unidad_medida,
        fecha = fecha,
        valor = valor
    )

    fun CurrencyEntry.fromDataToEntity() = CurrencyEntity(
        codigo = codigo,
        nombre = nombre,
        unidadMedida = unidad_medida,
        fecha = fecha,
        valor = valor
    )

    fun CurrencyEntity.fromEntityToDomain() = CurrencyModel(
        codigo = codigo,
        nombre = nombre,
        unidadMedida = unidadMedida,
        fecha = fecha,
        valor = valor
    )

    fun CurrencyModel.fromDomainToEntity() = CurrencyEntity(
        codigo = codigo,
        nombre = nombre,
        unidadMedida = unidadMedida,
        fecha = fecha,
        valor = valor
    )

}