package com.lumisdinos.mindicador.data.remote

import com.google.gson.Gson
import com.lumisdinos.mindicador.common.Resource
import com.lumisdinos.mindicador.common.ResourceState
import com.lumisdinos.mindicador.data.Constants.WRONG_SERVER_DATA
import com.lumisdinos.mindicador.data.remote.model.CurrencyEntry
import org.json.JSONObject
import javax.inject.Inject

class CurrencyRemoteSourceImpl @Inject constructor(
    private val restApi: CurrencyRestApi
) : CurrencyRemoteSource {

    override suspend fun getAllCurrencies(): Resource<List<CurrencyEntry>> {
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
                        list.add(Gson().fromJson(item.toString(), CurrencyEntry::class.java))
                    }
                }
            }
            return if (list.size == 0) {
                Resource(ResourceState.ERROR, null, WRONG_SERVER_DATA)
            } else{
                Resource(ResourceState.SUCCESS, list)
            }

        } catch (e: Exception) {
            return Resource(ResourceState.ERROR, null, e.message)
        }
    }
}