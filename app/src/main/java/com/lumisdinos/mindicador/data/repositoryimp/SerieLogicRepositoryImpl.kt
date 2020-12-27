package com.lumisdinos.mindicador.data.repositoryimp

import com.lumisdinos.mindicador.domain.model.SerieStateModel
import com.lumisdinos.mindicador.domain.repos.*
import timber.log.Timber
import javax.inject.Inject

class SerieLogicRepositoryImpl @Inject constructor(
    private val serieStateRepo: SerieStateRepository
) : SerieLogicRepository {

    override fun share() {
        Timber.d("qwer share")
    }

    override suspend fun messageIsShown(currencyCode: String) {
        Timber.d("qwer messageIsShown")
        var serieState = serieStateRepo.getSerieState(currencyCode)
        if (serieState == null) {
            serieState = SerieStateModel()
        }
        serieState = serieState.copy(errorMessage = null)
        serieStateRepo.insertSerieState(serieState)
    }

}