package com.example.weatherapp.core

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class AppSharedPreferences @Inject constructor(
    val sharedPreferences: SharedPreferences
) {
    private companion object {
        const val KEY_LATITUDE = "latitude"
        const val KEY_LONGITUDE = "longitude"
        const val CAIRO_LATITUDE = 30.05f
        const val CAIRO_LONGITUDE = 31.25f
        const val LANGUAGE = "language"
        const val DEFAULT_LANGUAGE = "en"
    }

    fun saveLocation(latitude: Float, longitude: Float) {
        sharedPreferences.edit {
            putFloat(KEY_LATITUDE, latitude).putFloat(KEY_LONGITUDE, longitude)
        }
    }

    fun saveLanguage(lang: String) {
        sharedPreferences.edit {
            putString(LANGUAGE, lang)
        }
    }

    fun getLanguage(): String? {
        return sharedPreferences.getString(LANGUAGE, DEFAULT_LANGUAGE)
    }

    fun getLatitude(): Float {
        return sharedPreferences.getFloat(KEY_LATITUDE, CAIRO_LATITUDE)
    }

    fun getLongitude(): Float {
        return sharedPreferences.getFloat(KEY_LONGITUDE, CAIRO_LONGITUDE)
    }
}