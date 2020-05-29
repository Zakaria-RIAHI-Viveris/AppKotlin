package com.viveris.appkotlin.extention

import java.text.SimpleDateFormat
import java.util.*

fun Int?.dateToString(): String = when (this != null) {
    true -> {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this!! * 1000L

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.format(calendar.time)
    }
    else -> ""
}