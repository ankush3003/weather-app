package com.exercise.weatherapp.models

/**
 * Entry for single day
 */
data class WeatherData(
    val dt: Long? = null,
    val main: MainTemp? = null,
    val weather: List<Weather>? = null
)

data class MainTemp (
    val temp_min: Float? = null,
    val temp_max: Float? = null,
    var humidity: Double? = null,
    var pressure: Double? = null
)

data class Weather (
    val description: String? = null
)