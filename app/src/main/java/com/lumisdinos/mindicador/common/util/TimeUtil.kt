package com.lumisdinos.mindicador.common.util

import com.lumisdinos.mindicador.data.Constants.DATE_FORMAT_FROM
import com.lumisdinos.mindicador.data.Constants.DATE_FORMAT_TO
import java.text.SimpleDateFormat
import java.util.*


fun convertDateFormat(
    dateStr: String?,
    fromFormat: String = DATE_FORMAT_FROM,
    toFormat: String = DATE_FORMAT_TO
): String? {
    if (dateStr.isNullOrEmpty()) return null
    val parser = SimpleDateFormat(fromFormat, Locale.getDefault())
    val formatter = SimpleDateFormat(toFormat, Locale.getDefault())
    try {
        val date = parser.parse(dateStr) ?: return null
        val formattedDate = formatter.format(date)
        val backFormattedDateStr = parser.format(date)
        return if (dateStr.equals(backFormattedDateStr, ignoreCase = true)) {
            formattedDate
        } else {
            null
        }
    } catch (e: Exception) {
        return null
    }
}