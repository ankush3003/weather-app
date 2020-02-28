package com.exercise.weatherapp.models

import java.util.*

data class WeatherData(
    val minTemp: Double? = null,
    val maxTemp: Double? = null,
    val date: Date? = null,
    var humidity: Double? = null,
    var pressure: Double? = null
)
