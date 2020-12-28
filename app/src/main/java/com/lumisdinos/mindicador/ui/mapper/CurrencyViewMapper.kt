package com.lumisdinos.mindicador.ui.mapper

import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.ui.model.CurrencyView
import javax.inject.Inject

class CurrencyViewMapper @Inject constructor() {

    fun CurrencyModel.fromDomainToView() = CurrencyView(
        codigo = codigo,
        nombre = nombre,
        unidadMedida = unidadMedida,
        fecha = fecha,
        valor = valor
    )

}