package com.lumisdinos.mindicador.data.remote

import com.google.gson.Gson
import com.lumisdinos.mindicador.common.Resource
import com.lumisdinos.mindicador.common.ResourceState
import com.lumisdinos.mindicador.data.Constants.WRONG_SERVER_DATA
import com.lumisdinos.mindicador.data.remote.model.CurrencyEntry
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

class CurrencyRemoteSourceImpl @Inject constructor(
    private val restApi: CurrencyRestApi
) : CurrencyRemoteSource {

    override suspend fun getAllCurrencies(): Resource<List<CurrencyEntry>> {
        Timber.d("qwer CurrencyRemoteSourceImpl getAllCurrencies")
        try {
            val response = restApi.getAllCurrencies()
            if (!response.isSuccessful) return Resource(
                ResourceState.ERROR,
                null,
                response.message()
            )
            val list = mutableListOf<CurrencyEntry>()
            response.body().let {
                val json = JSONObject(it?.string().orEmpty())
                val keys: Iterator<*> = json.keys().iterator()
                while (keys.hasNext()) {
                    val key = keys.next() as String
                    if (json.get(key) is JSONObject) {
                        val item = JSONObject(json.get(key).toString())
                        //Timber.d("qwer getAllCurrencies item: %s", item)
                        list.add(Gson().fromJson(item.toString(), CurrencyEntry::class.java))
                    }
                }
            }
            Timber.d("qwer getAllCurrencies list.size: %s, list: %s", list.size, list)
            return if (list.size == 0) {
                Resource(ResourceState.ERROR, null, WRONG_SERVER_DATA)
            } else{
                Resource(ResourceState.SUCCESS, list)
            }

        } catch (e: Exception) {
            return Resource(ResourceState.ERROR, null, e.message)
        }
    }


//    override suspend fun getAllCurrencies(): Resource<List<CurrencyEntry>> {
//        try {
//            val response = restApi.getAllCurrencies()
//            if (!response.isSuccessful) return Resource(
//                ResourceState.ERROR,
//                null,
//                response.message()
//            )
//
//            val body = response.body()
//            if (body is Map<String, Any>) {
//                val list = mutableListOf<CurrencyEntry>()
//                for ((k, v) in body) {
//                    val map: Map<String, Any>? = v as? Map<String, Any>
//                    map?.let {
//                        var codigo = ""
//                        var nombre = ""
//                        var unidadMedida = ""
//                        var fecha = ""
//                        var valor = 0.0
//
//                        for ((k2, v2) in it) {
//
//                            when (k2) {
//                                CURRENCY_CODIGO -> codigo = v2.toString()
//                                CURRENCY_NOMBRE -> nombre = v2.toString()
//                                CURRENCY_UNIDAD_MEDIDA -> unidadMedida = v2.toString()
//                                CURRENCY_FECHA -> fecha = v2.toString()
//                                CURRENCY_VALOR -> valor = strToDouble(v2.toString())
//                            }
//                        }
//                        val currencyEntry =
//                            CurrencyEntry(codigo, nombre, unidadMedida, fecha, valor)
//                        list.add(currencyEntry)
//                    }
//                    return Resource(ResourceState.SUCCESS, list)
//                }
//            }
//            return Resource(ResourceState.ERROR, null, WRONG_SERVER_DATA)
//        } catch (e: Exception) {
//            return Resource(ResourceState.ERROR, null, e.message)
//        }
//    }


//    override suspend fun getAllCurrencies(): IndicadorEntry? {//List<CurrencyEntry>?
//        Timber.d("qwer getAllCurrencies")
//        //val indicador = restApi.getAllCurrencies()
//
//
//        try {
//            val response = restApi.getAllCurrencies()
//            if (response.isSuccessful) {
//                Timber.d(
//                    "qwer getAllCurrencies response.isSuccessful: %s",
//                    response.body().toString()
//                )
//                Timber.d("qwer getAllCurrencies response: %s", response.toString())
////                Timber.d("qwer getAllCurrencies response.raw: %s", response.raw())
////                Timber.d("qwer getAllCurrencies response.code: %s", response.code())
////                Timber.d("qwer getAllCurrencies response.errorBody: %s", response.errorBody())
////                Timber.d("qwer getAllCurrencies response.headers: %s", response.headers())
////                Timber.d("qwer getAllCurrencies response.message: %s", response.message())
//
//
//                //val body: LinkedTreeMap<String, Any>? = response.body() as? LinkedTreeMap<String, Any>//com.google.gson.internal.LinkedTreeMap
//                val body = response.body()
//
//                if (body is Map<String, Any>) {
//                    Timber.d("qwer getAllCurrencies if body is Map): %s ", body)
//                    //MyClass object = new Gson().fromJson(new Gson().toJson(((LinkedTreeMap<String, Object>) theLinkedTreeMapObject)), MyClass .class)
//                    val list = mutableListOf<CurrencyEntry>()
//                    for ((k, v) in body) {
////                        Timber.d("qwer  k: %s, v: %s ", k, v)
//                        Timber.d(
//                            "qwer  k: %s, v: %s ",
//                            k.javaClass.kotlin.qualifiedName,
//                            v.javaClass.kotlin.qualifiedName
//                        )
//                        val map: Map<String, Any>? = v as? Map<String, Any>
//                        map?.let {
//                            Timber.d("qwer getAllCurrencies map?.let")
//                            var codigo = ""
//                            var nombre = ""
//                            var unidadMedida = ""
//                            var fecha = ""
//                            var valor = 0.0
//
//                            for ((k2, v2) in it) {
//                                Timber.d("qwer  k2: %s, v2: %s ", k2, v2)
////                                Timber.d(
////                                    "qwer  k2: %s, v2: %s ",
////                                    k2.javaClass.kotlin.qualifiedName,
////                                    v2.javaClass.kotlin.qualifiedName
////                                )
//                                Timber.d("qwer                                      ")
//                                Timber.d("qwer                                      ")
//
//                                when (k2) {
//                                    CURRENCY_CODIGO -> codigo = v2.toString()
//                                    CURRENCY_NOMBRE -> nombre = v2.toString()
//                                    CURRENCY_UNIDAD_MEDIDA -> unidadMedida = v2.toString()
//                                    CURRENCY_FECHA -> fecha = v2.toString()
//                                    CURRENCY_VALOR -> valor = strToDouble(v2.toString())
//                                }
//                            }
//                            val currencyEntry =
//                                CurrencyEntry(codigo, nombre, unidadMedida, fecha, valor)
//                            list.add(currencyEntry)
//                        }
//                    }
//                    Timber.d("qwer  list.size: %s, list: %s ", list.size, list.toString())
//                } else {
//                    Timber.d("qwer getAllCurrencies if body NOT Map) ")
//                }
//
//            } else {
//                Timber.d("qwer getAllCurrencies response.failure: %s", response.body())
//            }
//        } catch (e: UnknownHostException) {
//            Timber.d("qwer getAllCurrencies UnknownHostException: %s", e.message)
//            //this exception occurs when there is no internet connection or host is not available
//            //so inform user that something went wrong
//        } catch (e: SocketTimeoutException) {
//            Timber.d("qwer getAllCurrencies SocketTimeoutException: %s", e.message)
//            //this exception occurs when time out will happen
//            //so inform user that something went wrong
//        } catch (e: Exception) {
//            Timber.d("qwer getAllCurrencies Exception: %s", e.message)
//            //this is generic exception handling
//            //so inform user that something went wrong
//        }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        return null
//    }


//    override suspend fun getAllCurrencies(): Response<List<CurrencyEntry>>? {
//        Timber.d("qwer getAllCurrencies")
//
//
//        try {
//            val response = restApi.getAllCurrencies()
//            if (response.isSuccessful) {
//                Timber.d(
//                    "qwer getAllCurrencies response.isSuccessful: %s",
//                    response.body().toString()
//                )
//                Timber.d("qwer getAllCurrencies response: %s", response.toString())
////                Timber.d("qwer getAllCurrencies response.raw: %s", response.raw())
////                Timber.d("qwer getAllCurrencies response.code: %s", response.code())
////                Timber.d("qwer getAllCurrencies response.errorBody: %s", response.errorBody())
////                Timber.d("qwer getAllCurrencies response.headers: %s", response.headers())
////                Timber.d("qwer getAllCurrencies response.message: %s", response.message())
//
//
//                //val body: LinkedTreeMap<String, Any>? = response.body() as? LinkedTreeMap<String, Any>//com.google.gson.internal.LinkedTreeMap
//                val body = response.body()
//
//                if (body is LinkedTreeMap) {
//                    Timber.d("qwer getAllCurrencies if body is String): %s ", body)
//
//                    //MyClass object = new Gson().fromJson(new Gson().toJson(((LinkedTreeMap<String, Object>) theLinkedTreeMapObject)), MyClass .class)
//
//
//
//
//                } else {
//                    Timber.d("qwer getAllCurrencies if body NOT String) ")
//                }
//
//            } else {
//                Timber.d("qwer getAllCurrencies response.failure: %s", response.body())
//            }
//        } catch (e: UnknownHostException) {
//            Timber.d("qwer getAllCurrencies UnknownHostException: %s", e.message)
//            //this exception occurs when there is no internet connection or host is not available
//            //so inform user that something went wrong
//        } catch (e: SocketTimeoutException) {
//            Timber.d("qwer getAllCurrencies SocketTimeoutException: %s", e.message)
//            //this exception occurs when time out will happen
//            //so inform user that something went wrong
//        } catch (e: Exception) {
//            Timber.d("qwer getAllCurrencies Exception: %s", e.message)
//            //this is generic exception handling
//            //so inform user that something went wrong
//        }
//
//
//
//
//
//
//
//
//
//
//        //Timber.d("qwer getAllCurrencies return")
//        return null
//    }

}