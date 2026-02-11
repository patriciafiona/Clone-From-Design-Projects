package com.patriciafiona.taskplanner.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateTimeHelper {

    fun getCurrentDate(format: String = "EEEE, MMMM d"): String {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }
}