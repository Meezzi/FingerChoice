package com.meezzi.fingerchoice.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateFormatText {

    private const val DATE_PATTERN = "yyyy.MM.dd"
    private val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)

    fun getCurrentTime(): String {
        val currentTime = LocalDateTime.now()
        return applyDateFormat(currentTime)
    }

    fun applyDateFormat(currentTime: LocalDateTime): String {
        return currentTime.format(formatter)
    }
}