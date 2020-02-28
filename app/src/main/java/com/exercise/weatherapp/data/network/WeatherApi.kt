package com.exercise.weatherapp.data.network

import com.exercise.weatherapp.models.WeatherData
import com.exercise.weatherapp.models.WeatherForecast
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApi{
    @GET("/data/2.5/forecast?zip=94040,us&appid=826aa7ea5eb4130415dcec33af0f8d6b")
    suspend fun getWeatherForecast(): Response<WeatherForecast>

    @GET("/data/2.5/weather?zip=94040,us&APPID=826aa7ea5eb4130415dcec33af0f8d6b")
    suspend fun getTodayWeather(): Response<WeatherData>
}