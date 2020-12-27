package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.common.MessageType
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import com.lumisdinos.mindicador.domain.repos.*
import timber.log.Timber
import javax.inject.Inject

class SerieLogicRepositoryImpl @Inject constructor(
    private val serieStateRepo: SerieStateRepository,
    private val currencyRepo: CurrencyRepository,
) : SerieLogicRepository {

    override suspend fun share(currencyCode: String) {
        val currency = currencyRepo.getCurrency(currencyCode)
        Timber.d("qwer share currency: %s", currency)
        currency?.let {
            val sharedMessage = "${it.nombre} - ${it.valor}"
            setSharedMessage(currencyCode, sharedMessage)
        }
    }

    override suspend fun messageIsShown(currencyCode: String, type: String) {
        Timber.d("qwer messageIsShown")
        var serieState = getState(currencyCode)
        when(type) {
            MessageType.ERROR.name -> {serieState = serieState.copy(errorMessage = null)}
            MessageType.SHARED.name -> {serieState = serieState.copy(sharedMessage = null)}
        }
        serieStateRepo.insertSerieState(serieState)
    }

    private fun getState(currencyCode: String): SerieStateModel {
        val ser = serieStateRepo.getSerieState(currencyCode)
        Timber.d("qwer getState serieState: %s", ser)
        return serieStateRepo.getSerieState(currencyCode) ?: SerieStateModel(codigo = currencyCode)
    }

    private fun setSharedMessage(currencyCode: String, sharedMessage: String) {
        Timber.d("qwer setSharedMessage currencyCode: %s, sharedMessage: %s", currencyCode, sharedMessage)
        var serieState = getState(currencyCode)
        Timber.d("qwer setSharedMessage serieState: %s", serieState)
        serieState = serieState.copy(sharedMessage = sharedMessage)
        Timber.d("qwer setSharedMessage serieState: %s", serieState)
        serieStateRepo.insertSerieState(serieState)
    }

}