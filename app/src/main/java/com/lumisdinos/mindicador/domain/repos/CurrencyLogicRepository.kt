package com.lumisdinos.mindicador.domain.repos

import com.lumisdinos.mindicador.domain.model.CurrencyModel

interface CurrencyLogicRepository {

//    fun getCurrencyStateFlow(): Flow<CurrencyStateModel>
//
//    fun getCurrenciesFlow(): Flow<List<CurrencyModel>>

    //fun filterByCodigoOrName(value: String)

    suspend fun changeOrder(): List<CurrencyModel>

    suspend fun messageIsShown()

}