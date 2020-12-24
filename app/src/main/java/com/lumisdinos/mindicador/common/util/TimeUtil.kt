package com.lumisdinos.mindicador.common.util

import com.lumisdinos.mindicador.data.Constants.DATE_FORMAT_FROM
import com.lumisdinos.mindicador.data.Constants.DATE_FORMAT_TO
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


fun convertDateFormat(
    dateStr: String?,
    fromFormat: String = DATE_FORMAT_FROM,
    toFormat: String = DATE_FORMAT_TO
): String? {
    Timber.d(
        "qwer convertDateFormat dateStr: %s,  fromFormat: %s, toFormat: %s",
        dateStr,
        fromFormat,
        toFormat
    )
    if (dateStr.isNullOrEmpty()) return null
    val parser = SimpleDateFormat(fromFormat, Locale.getDefault())
    val formatter = SimpleDateFormat(toFormat, Locale.getDefault())
    try {
        val date = parser.parse(dateStr) ?: return null
        val formattedDate = formatter.format(date)

        val backFormattedDateStr = parser.format(date)
        Timber.d(
            "qwer convertDateFormat formattedDate: %s, backFormattedDateStr: %s",
            formattedDate,
            backFormattedDateStr
        )
        if (dateStr.equals(backFormattedDateStr, ignoreCase = true)) {
            Timber.d("qwer convertDateFormat -> return formattedDate: %s", formattedDate)
            return formattedDate
        } else {
            Timber.d("qwer convertDateFormat -> return null")
            return null
        }
    } catch (e: Exception) {
        Timber.d("qwer convertDateFormat Exception: %s", e.message)
        return null
    }
}