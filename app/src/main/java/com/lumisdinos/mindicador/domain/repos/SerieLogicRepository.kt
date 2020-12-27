package com.lumisdinos.mindicador.domain.repos

interface SerieLogicRepository {

    fun share()

    suspend fun messageIsShown(currencyCode: String)

}