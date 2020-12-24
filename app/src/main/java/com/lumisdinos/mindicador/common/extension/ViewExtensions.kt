package com.lumisdinos.mindicador.common.extension

import android.view.View
import com.lumisdinos.mindicador.common.listener.SingleClickListener

fun View.setSingleOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SingleClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}