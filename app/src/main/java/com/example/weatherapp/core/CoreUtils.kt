package com.example.weatherapp.core

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

fun String.handleImageUrl(): String {
    return if (this.startsWith("//")) "https:$this" else this
}

fun dateFormate(unformattedDate: String): String {

    val outputFormatter =
        DateTimeFormatter.ofPattern("EEE dd", Locale.ENGLISH)

    return try {

        val dateTime = LocalDateTime.parse(
            unformattedDate,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        )

        dateTime.format(outputFormatter)

    } catch (_: DateTimeParseException) {

        val date = LocalDate.parse(
            unformattedDate,
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        )

        date.format(outputFormatter)
    }
}