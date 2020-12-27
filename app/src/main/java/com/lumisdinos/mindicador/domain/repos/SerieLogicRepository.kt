package com.lumisdinos.mindicador.domain.repos

interface SerieLogicRepository {

    suspend fun share(currencyCode: String)

    suspend fun messageIsShown(currencyCode: String, type: String)

}