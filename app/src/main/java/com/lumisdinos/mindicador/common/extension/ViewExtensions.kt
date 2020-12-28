package com.lumisdinos.mindicador.common.extension

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.lumisdinos.mindicador.common.listener.SingleClickListener

fun View.setSingleOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SingleClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun Activity.showKeyboard() {
    showKeyboard(currentFocus ?: View(this))
}

fun Fragment.showKeyboard() {
    view?.let {
        activity?.showKeyboard(it) }
}

fun Fragment.hideKeyboard() {
    view?.let {
        activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

