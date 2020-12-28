package com.lumisdinos.mindicador.data.remote

import com.google.gson.Gson
import com.lumisdinos.mindicador.common.Resource
import com.lumisdinos.mindicador.common.ResourceState
import com.lumisdinos.mindicador.data.Constants
import com.lumisdinos.mindicador.data.remote.model.SerieEntry
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class SerieRemoteSourceImpl @Inject constructor(
    private val restApi: CurrencyRestApi
) : SerieRemoteSource {

    override suspend fun getSerieForMonth(currencyCode: String): Resource<List<SerieEntry>> {
        try {
            val response = restApi.getCurrencyForMonth(currencyCode)
            if (!response.isSuccessful) return Resource(
                ResourceState.ERROR,
                null,
                response.message()
            )
            val list = mutableListOf<SerieEntry>()
            response.body().let {
                val json = JSONObject(it?.string().orEmpty())
                val keys: Iterator<*> = json.keys().iterator()
                while (keys.hasNext()) {
                    val key = keys.next() as String
                    if (json.get(key) is JSONArray) {//todo:
                        val jsonArray = JSONArray(json.get(key).toString())
                        for (i in 0 until jsonArray.length()) {
                            val item = jsonArray[i]
                            list.add(Gson().fromJson(item.toString(), SerieEntry::class.java))
                        }
                    }
                }
            }
            return if (list.size == 0) {
                Resource(ResourceState.ERROR, null, Constants.WRONG_SERVER_DATA)
            } else {
                Resource(ResourceState.SUCCESS, list)
            }

        } catch (e: Exception) {
            return Resource(ResourceState.ERROR, null, e.message)
        }
    }

}