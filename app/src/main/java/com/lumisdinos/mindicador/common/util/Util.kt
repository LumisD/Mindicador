package com.lumisdinos.mindicador.common.util

import com.lumisdinos.mindicador.common.extension.stringToInt
import com.lumisdinos.mindicador.common.AppConfig.previousClickTimeMillis
import com.lumisdinos.mindicador.data.Constants.LONG_DELAY
import com.lumisdinos.mindicador.data.Constants.SHORT_DELAY


fun strToInt(string: String, default: Int = 0): Int {
    return try {
        string.toInt()
    } catch (nfe: NumberFormatException) {
        default
    }
}


fun strToDouble(string: String, default: Double = 0.0): Double {
    return try {
        string.toDouble()
    } catch (nfe: NumberFormatException) {
        default
    }
}


fun numbToStr(number: Number?, default: String = "0"): String {
    return try {
        number.toString()
    } catch (e: Exception) {
        default
    }
}


fun convertString2IntList(listAsString: String): MutableList<Int> {
    val list = listAsString.split(",").map {
        it.trim().stringToInt(-1)
    }.filter { it != -1 }.toMutableList()
    return list
}


fun convertIntList2String(list: List<Int>): String {
    return list.joinToString(",").trim()
}


fun isClickedSingle(): Boolean {
    return isAlreadyClick(LONG_DELAY)
}


fun isClickedShort(): Boolean {
    return isAlreadyClick(SHORT_DELAY)
}


fun isAlreadyClick(time: Long): Boolean {
    val currentTimeMillis = System.currentTimeMillis()
    if (currentTimeMillis >= previousClickTimeMillis + time) {
        previousClickTimeMillis = currentTimeMillis
        return false
    } else {
        return true
    }
}