package com.example.weatherapp.core

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale


const val ARABIC = "ar"
const val ENGLISH = "en"
const val ENGLISH_POSITION = 0
const val ARABIC_POSITION = 1
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

fun changeLang(language: String) {
    if (language == ARABIC) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(ARABIC)
        )
    } else {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(ENGLISH)
        )
    }
}