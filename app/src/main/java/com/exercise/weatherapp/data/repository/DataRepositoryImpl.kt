package com.exercise.weatherapp.data.repository

import com.exercise.weatherapp.data.network.WeatherApi
import com.exercise.weatherapp.models.WeatherData

class DataRepositoryImpl(val api: WeatherApi) : IDataRepository {
    override suspend fun getTodoList(): UseCaseResult<List<WeatherData>> {
        return try {
            val result = api.getTodoListData()
            if (result.isSuccessful)
                UseCaseResult.Success(result.body() as List<WeatherData>)
            //Q: Type casting is required everywhere...how can we avoid this
            else UseCaseResult.Error(RuntimeException("test error"))
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}

