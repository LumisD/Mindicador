package com.lumisdinos.mindicador.ui.mapper

import com.lumisdinos.mindicador.factory.ModelFactory.makeCurrencyModel
import org.junit.Assert
import org.junit.Test

class CurrencyViewMapperTest {

    private val mapper = CurrencyViewMapper()

    @Test
    fun `from CurrencyModel to CurrencyView`() {

        val model = makeCurrencyModel()
        val view = with(mapper) { model.fromDomainToView() }

        Assert.assertEquals(model.codigo, view.codigo)
        Assert.assertEquals(model.nombre, view.nombre)
        Assert.assertEquals(model.unidadMedida, view.unidadMedida)
        Assert.assertEquals(model.fecha, view.fecha)
        Assert.assertEquals(model.valor, view.valor)
    }
}