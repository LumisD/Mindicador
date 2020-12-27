package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.CurrencyModel

interface CurrencyLogicRepository {

    suspend fun changeOrder(): List<CurrencyModel>

    suspend fun messageIsShown()

}