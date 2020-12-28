package com.lumisdinos.mindicador.data.mapper

import com.lumisdinos.mindicador.factory.ModelFactory
import org.junit.Assert
import org.junit.Test

class CurrencyDataMapperTest {

    private val mapper = CurrencyDataMapper()

    @Test
    fun `given CurrencyEntry, when fromDataToDomain, then return CurrencyModel`() {

        val entry = ModelFactory.makeCurrencyEntry()
        val model = with(mapper) { entry.fromDataToDomain() }

        Assert.assertEquals(entry.codigo, model.codigo)
        Assert.assertEquals(entry.nombre, model.nombre)
        Assert.assertEquals(entry.unidad_medida, model.unidadMedida)
        Assert.assertEquals(entry.fecha, model.fecha)
        Assert.assertEquals(entry.valor, model.valor)
    }

    @Test
    fun `given CurrencyEntity, when fromEntityToDomain, then return CurrencyModel`() {

        val entity = ModelFactory.makeCurrencyEntity()
        val model = with(mapper) { entity.fromEntityToDomain() }

        Assert.assertEquals(entity.codigo, model.codigo)
        Assert.assertEquals(entity.nombre, model.nombre)
        Assert.assertEquals(entity.unidadMedida, model.unidadMedida)
        Assert.assertEquals(entity.fecha, model.fecha)
        Assert.assertEquals(entity.valor, model.valor)
    }

    @Test
    fun `given CurrencyModel, when fromDomainToEntity, then return CurrencyEntity`() {

        val model = ModelFactory.makeCurrencyModel()
        val entity = with(mapper) { model.fromDomainToEntity() }

        Assert.assertEquals(entity.codigo, model.codigo)
        Assert.assertEquals(entity.nombre, model.nombre)
        Assert.assertEquals(entity.unidadMedida, model.unidadMedida)
        Assert.assertEquals(entity.fecha, model.fecha)
        Assert.assertEquals(entity.valor, model.valor)
    }

}