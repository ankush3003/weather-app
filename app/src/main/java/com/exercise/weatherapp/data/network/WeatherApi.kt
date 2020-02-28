package com.exercise.weatherapp.data.network

import com.exercise.weatherapp.models.WeatherData
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApi{
    //Q: What is suspend, why it's required,  Tyoe casting is required and woud be multiple, how can we avoid
    @GET("/todos")
    suspend fun getTodoListData(): Response<List<WeatherData>>
}