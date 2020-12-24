package com.lumisdinos.mindicador.ui.mapper

import com.lumisdinos.mindicador.domain.model.SerieModel
import com.lumisdinos.mindicador.ui.model.SerieView
import javax.inject.Inject

class SerieViewMapper @Inject constructor() {

    fun SerieModel.fromDomainToView() = SerieView(
        id = id,
        currencyId = currencyId,
        fecha = fecha,
        valor = valor
    )

}