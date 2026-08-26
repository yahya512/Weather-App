package com.example.weatherapp.core

fun String.handleImageUrl(): String {
    return if (this.startsWith("//")) "https:$this" else this
}