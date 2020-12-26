package com.lumisdinos.mindicador.data.mapper

import com.lumisdinos.mindicador.data.local.model.CurrencyStateEntity
import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import javax.inject.Inject

class CurrencyStateDataMapper @Inject constructor() {

    fun CurrencyStateEntity.fromEntityToDomain() =
        CurrencyStateModel(
            updateTime = updateTime,
            errorMessage = errorMessage,
            order = order
        )

    fun CurrencyStateModel.fromDomainToEntity() = CurrencyStateEntity(
        updateTime = updateTime,
        errorMessage = errorMessage,
        order = order
    )

}