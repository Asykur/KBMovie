package com.asykurkhamid.kitabisamovie.utils

import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

open class BaseActivity: AppCompatActivity() {

    fun toGMTFormat(date: String): String? {
        val formatter = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = formatter.parse("$date")
        val sdf = SimpleDateFormat("dd MMM yyyy")
        val result = sdf.format(dateTime)
        return result
    }
}