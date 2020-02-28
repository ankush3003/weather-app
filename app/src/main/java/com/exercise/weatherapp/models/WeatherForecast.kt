package com.exercise.weatherapp.models

/**
 * Entry for 5 days/ weather forecast
 */
data class WeatherForecast(
    val list: List<WeatherData>? = null,
    val city: City? = null
)

data class City (
    val name: String? = null
)