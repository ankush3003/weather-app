package com.exercise.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*


object DateUtils {
    fun getDateAsString(timestamp: Long): String {
        return formatDateTime("dd-MMM-yyyy", timestamp)
    }

    private fun formatDateTime(format: String, timestamp: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = timestamp

        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(cal.time)
    }
}