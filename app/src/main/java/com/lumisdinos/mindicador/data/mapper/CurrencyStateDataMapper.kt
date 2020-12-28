package com.lumisdinos.mindicador.data.mapper

import com.lumisdinos.mindicador.data.local.model.CurrencyStateEntity
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import javax.inject.Inject

class CurrencyStateDataMapper @Inject constructor() {

    fun CurrencyStateEntity.fromEntityToDomain() =
        CurrencyStateModel(
            errorMessage = errorMessage,
            order = order,
            loading = loading
        )

    fun CurrencyStateModel.fromDomainToEntity() = CurrencyStateEntity(
        errorMessage = errorMessage,
        order = order,
        loading = loading
    )

}