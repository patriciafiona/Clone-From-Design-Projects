package com.patriciafiona.taskplanner.utils

import java.util.Calendar

object Greetings {
    fun generateGreetings(): String {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when {
            currentHour < 12 -> "Good Morning"
            currentHour < 18 -> "Good Afternoon"
            else -> "Good Evening"
        }
    }
}