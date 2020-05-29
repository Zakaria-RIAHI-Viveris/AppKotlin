package com.viveris.appkotlin.extention

import android.support.annotation.StringRes
import android.support.v4.app.FragmentActivity
import android.widget.Toast

fun FragmentActivity?.toast(text: String, duration: Int = Toast.LENGTH_SHORT) = this?.apply { Toast.makeText(this, text, duration).show() }

fun FragmentActivity?.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) = this?.apply { Toast.makeText(this, getString(resId), duration).show() }