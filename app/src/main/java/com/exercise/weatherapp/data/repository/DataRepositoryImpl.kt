package com.exercise.weatherapp.data.repository

import com.exercise.weatherapp.data.network.WeatherApi
import com.exercise.weatherapp.models.WeatherData
import com.exercise.weatherapp.models.WeatherForecast

class DataRepositoryImpl(val api: WeatherApi) : IDataRepository {
    override suspend fun getWeatherForecast(): UseCaseResult<WeatherForecast> {
        return try {
            val result = api.getWeatherForecast()
            if (result.isSuccessful)
                UseCaseResult.Success(result.body() as WeatherForecast)
            //Q: Type casting is required everywhere...how can we avoid this
            else UseCaseResult.Error(RuntimeException("test error"))
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
    override suspend fun getTodayWeather(): UseCaseResult<WeatherData> {
        return try {
            val result = api.getTodayWeather()
            if (result.isSuccessful)
                UseCaseResult.Success(result.body() as WeatherData)
            //Q: Type casting is required everywhere...how can we avoid this
            else UseCaseResult.Error(RuntimeException("test error"))
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}

