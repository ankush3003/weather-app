package com.exercise.weatherapp.data.repository

import com.exercise.weatherapp.models.WeatherData


interface IDataRepository {
    suspend fun getTodoList(): UseCaseResult<List<WeatherData>>
}