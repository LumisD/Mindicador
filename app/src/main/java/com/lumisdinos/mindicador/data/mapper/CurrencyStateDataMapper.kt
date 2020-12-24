package com.lumisdinos.mindicador.data.mapper

import com.lumisdinos.mindicador.data.local.model.CurrencyStateEntity
import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import javax.inject.Inject

class CurrencyStateDataMapper @Inject constructor() {

    fun CurrencyStateEntity.fromEntityToDomain(currencies: List<CurrencyModel>) = CurrencyStateModel(
        currencies = currencies,
        isCurrenciesUpdated = isCurrenciesUpdated,
        updateTime = updateTime,
        errorMessage = errorMessage
    )

    fun CurrencyStateModel.fromDomainToEntity() = CurrencyStateEntity(
        isCurrenciesUpdated = isCurrenciesUpdated,
        updateTime = updateTime,
        errorMessage = errorMessage
    )

}