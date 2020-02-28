package com.exercise.weatherapp.data.network

import com.exercise.weatherapp.models.WeatherData
import com.exercise.weatherapp.models.WeatherForecast
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApi{
    //Q: What is suspend, why it's required,  Tyoe casting is required and woud be multiple, how can we avoid
    @GET("/data/2.5/forecast?q=London,uk&APPID=826aa7ea5eb4130415dcec33af0f8d6b")
    suspend fun getWeatherForecast(): Response<WeatherForecast>

    @GET("/data/2.5/weather?q=London,uk&APPID=826aa7ea5eb4130415dcec33af0f8d6b")
    suspend fun getTodayWeather(): Response<WeatherData>
}