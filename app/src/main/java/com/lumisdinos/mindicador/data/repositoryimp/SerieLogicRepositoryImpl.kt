package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.common.MessageType
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import com.lumisdinos.mindicador.domain.repos.*
import javax.inject.Inject

class SerieLogicRepositoryImpl @Inject constructor(
    private val serieStateRepo: SerieStateRepository,
    private val currencyRepo: CurrencyRepository,
) : SerieLogicRepository {

    override suspend fun share(currencyCode: String) {
        val currency = currencyRepo.getCurrency(currencyCode)
        currency?.let {
            val sharedMessage = "${it.nombre} - ${it.valor}"
            setSharedMessage(currencyCode, sharedMessage)
        }
    }

    override suspend fun messageIsShown(currencyCode: String, type: String) {
        var serieState = getState(currencyCode)
        when(type) {
            MessageType.ERROR.name -> {serieState = serieState.copy(errorMessage = null)}
            MessageType.SHARED.name -> {serieState = serieState.copy(sharedMessage = null)}
        }
        serieStateRepo.insertSerieState(serieState)
    }

    override suspend fun setLoading(isLoading: Boolean, currencyCode: String) {
        var serieState = getState(currencyCode)
        serieState = serieState.copy(
            loading = isLoading
        )
        serieStateRepo.insertSerieState(serieState)
    }


    private fun getState(currencyCode: String): SerieStateModel {
        val ser = serieStateRepo.getSerieState(currencyCode)
        return serieStateRepo.getSerieState(currencyCode) ?: SerieStateModel(codigo = currencyCode)
    }

    private fun setSharedMessage(currencyCode: String, sharedMessage: String) {
        var serieState = getState(currencyCode)
        serieState = serieState.copy(sharedMessage = sharedMessage)
        serieStateRepo.insertSerieState(serieState)
    }

}