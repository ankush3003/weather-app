package com.exercise.weatherapp.data.repository

import com.exercise.weatherapp.models.WeatherData
import com.exercise.weatherapp.models.WeatherForecast


interface IDataRepository {
    suspend fun getWeatherForecast(): UseCaseResult<WeatherForecast>
    suspend fun getTodayWeather(): UseCaseResult<WeatherData>
}