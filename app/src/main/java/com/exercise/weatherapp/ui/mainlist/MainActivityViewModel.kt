package com.exercise.weatherapp.ui.mainlist

import android.database.Observable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exercise.weatherapp.BR
import com.exercise.weatherapp.R
import com.exercise.weatherapp.data.repository.IDataRepository
import com.exercise.weatherapp.data.repository.UseCaseResult
import kotlinx.coroutines.*
import me.tatarka.bindingcollectionadapter2.ItemBinding
import com.exercise.weatherapp.models.WeatherData

class MainActivityViewModel(private val dataRepository: IDataRepository) : ViewModel(){
    private val job = Job()
    val weatherList = ObservableArrayList<WeatherData>()
    val showShimmerView = ObservableBoolean(true)
    val showErrorView = ObservableBoolean(false)
    val cityName: ObservableField<String> = ObservableField("")

    fun getTodayWeather() {
        showShimmerView.set(true)
        weatherList.clear()
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) { dataRepository.getTodayWeather() }
            when (result) {
                is UseCaseResult.Success -> {
                    showShimmerView.set(false)
                    showErrorView.set(false)

                    weatherList.clear()
                    weatherList.add(result.data)
                    cityName.set(result.data.name)
                }
                is UseCaseResult.Error -> {
                    showShimmerView.set(false)
                    showErrorView.set(true)
                }
            }
        }
    }

    fun getWeatherForecast() {
        showShimmerView.set(true)
        weatherList.clear()
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) { dataRepository.getWeatherForecast() }
            showShimmerView.set(false)
            when (result) {
                is UseCaseResult.Success -> {

                    result.data.list?.let {
                        showShimmerView.set(false)
                        showErrorView.set(false)

                        weatherList.clear()
                        weatherList.addAll(it)
                    }

                    result.data.city?.let{
                         cityName.set(it.name)
                    }
                }
                is UseCaseResult.Error -> {
                    showErrorView.set(true)
                    showShimmerView.set(false)
                }
            }
        }
    }

    fun reloadList() {
        if (weatherList.isNullOrEmpty()) {
            showErrorView.set(false)
            showShimmerView.set(true)
            getWeatherForecast()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    /**
     * The Single item.
     */
    val accessoryBinding = ItemBinding.of<WeatherData> { itemBinding, _, _ ->
        itemBinding.set(BR.itemViewModel, R.layout.layout_item_weather)
    }
}