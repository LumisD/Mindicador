package com.lumisdinos.mindicador.factory

import com.lumisdinos.mindicador.data.local.model.CurrencyEntity
import com.lumisdinos.mindicador.data.local.model.CurrencyStateEntity
import com.lumisdinos.mindicador.data.local.model.SerieEntity
import com.lumisdinos.mindicador.data.local.model.SerieStateEntity
import com.lumisdinos.mindicador.data.remote.model.CurrencyEntry
import com.lumisdinos.mindicador.data.remote.model.SerieEntry
import com.lumisdinos.mindicador.domain.model.CurrencyModel
import com.lumisdinos.mindicador.domain.model.CurrencyStateModel
import com.lumisdinos.mindicador.domain.model.SerieModel
import com.lumisdinos.mindicador.domain.model.SerieStateModel
import com.lumisdinos.mindicador.ui.model.CurrencyView
import com.lumisdinos.mindicador.ui.model.SerieView


object ModelFactory {

    fun makeCurrencyEntry() = CurrencyEntry(
        codigo = RandomFactory.generateString(),
        nombre = RandomFactory.generateString(),
        unidad_medida = RandomFactory.generateString(),
        fecha = RandomFactory.generateString(),
        valor = RandomFactory.generateDouble()
    )

    fun makeSerieEntry() = SerieEntry(
        fecha = RandomFactory.generateString(),
        valor = RandomFactory.generateDouble()
    )

    fun makeCurrencyEntity() = CurrencyEntity(
        codigo = RandomFactory.generateString(),
        nombre = RandomFactory.generateString(),
        unidadMedida = RandomFactory.generateString(),
        fecha = RandomFactory.generateString(),
        valor = RandomFactory.generateDouble()
    )

    fun makeCurrencyStateEntity() = CurrencyStateEntity(
        id = RandomFactory.generateInt(),
        errorMessage = RandomFactory.generateString(),
        order = RandomFactory.generateString(),
        loading = RandomFactory.generateBoolean()
    )

    fun makeSerieEntity() = SerieEntity(
        id = RandomFactory.generateInt(),
        currencyCode = RandomFactory.generateString(),
        fecha = RandomFactory.generateString(),
        valor = RandomFactory.generateDouble()
    )

    fun makeSerieStateEntity() = SerieStateEntity(
        codigo = RandomFactory.generateString(),
        errorMessage = RandomFactory.generateString(),
        sharedMessage = RandomFactory.generateString(),
        loading = RandomFactory.generateBoolean()
    )

    fun makeCurrencyModel() = CurrencyModel(
        codigo = RandomFactory.generateString(),
        nombre = RandomFactory.generateString(),
        unidadMedida = RandomFactory.generateString(),
        fecha = RandomFactory.generateString(),
        valor = RandomFactory.generateDouble()
    )

    fun makeCurrencyStateModel() = CurrencyStateModel(
        errorMessage = RandomFactory.generateString(),
        order = RandomFactory.generateString(),
        loading = RandomFactory.generateBoolean()
    )

    fun makeSerieModel() = SerieModel(
        id = RandomFactory.generateInt(),
        currencyCode = RandomFactory.generateString(),
        fecha = RandomFactory.generateString(),
        valor = RandomFactory.generateDouble()
    )

    fun makeSerieStateModel() = SerieStateModel(
        codigo = RandomFactory.generateString(),
        errorMessage = RandomFactory.generateString(),
        sharedMessage = RandomFactory.generateString(),
        loading = RandomFactory.generateBoolean()
    )

    fun makeCurrencyView() = CurrencyView(
        codigo = RandomFactory.generateString(),
        nombre = RandomFactory.generateString(),
        unidadMedida = RandomFactory.generateString(),
        fecha = RandomFactory.generateString(),
        valor = RandomFactory.generateDouble()
    )

    fun makeSerieView() = SerieView(
        id = RandomFactory.generateInt(),
        currencyCode = RandomFactory.generateString(),
        fecha = RandomFactory.generateString(),
        valor = RandomFactory.generateDouble()
    )

    fun makeCurrencyModelList(count: Int): List<CurrencyModel> {
        return if (count == 0) {
            arrayListOf()
        } else {
            (0..count).map { makeCurrencyModel() }
        }
    }

    fun makeCurrencyEntryList(count: Int): List<CurrencyEntry> {
        return if (count == 0) {
            arrayListOf()
        } else {
            (0..count).map { makeCurrencyEntry() }
        }
    }

}