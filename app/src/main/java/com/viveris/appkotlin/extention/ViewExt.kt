package com.viveris.appkotlin.extention

import android.view.View

fun View?.changeVisibility(isVisible: Boolean) = this?.apply {
    visibility = when (isVisible) {
        true -> View.VISIBLE
        else -> View.GONE
    }
}