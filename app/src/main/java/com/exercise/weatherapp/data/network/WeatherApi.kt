package com.exercise.weatherapp.data.network

import com.exercise.weatherapp.models.WeatherData
import com.exercise.weatherapp.models.WeatherForecast
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApi{
    //Q: What is suspend, why it's required,  Tyoe casting is required and woud be multiple, how can we avoid
    @GET("/data/2.5/forecast?zip=127021,in&appid=f4b98a08513742f455b15bc7e6f4016e")
    suspend fun getWeatherForecast(): Response<WeatherForecast>

    @GET("/data/2.5/weather?zip=94040,us&APPID=826aa7ea5eb4130415dcec33af0f8d6b")
    suspend fun getTodayWeather(): Response<WeatherData>
}