package com.lumisdinos.mindicador.ui.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import com.lumisdinos.mindicador.R
import com.lumisdinos.mindicador.data.Constants.TEN_SEC

@SuppressLint("WrongConstant")
fun showSnackbar(view: View, message: String, context: Context, action: () -> Unit) {
    val snackBar = Snackbar.make(
        view, message,
        Snackbar.LENGTH_LONG
    ).setAction(context.getString(R.string.try_again)) { action.invoke() }
    snackBar.setActionTextColor(ContextCompat.getColor(context, R.color.white))
    snackBar.setTextColor(ContextCompat.getColor(context, R.color.white))
    snackBar.duration = TEN_SEC//LENGTH_INDEFINITE
    val snackBarView = snackBar.view
    snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_500))
    val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.maxLines = 4
    snackBar.show()
}