package com.lumisdinos.mindicador.domain.repos

interface SerieLogicRepository {

    suspend fun share(currencyCode: String)

    suspend fun messageIsShown(currencyCode: String, type: String)

    suspend fun setLoading(isLoading: Boolean, currencyCode: String)

}