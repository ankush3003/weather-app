package com.exercise.weatherapp.ui.mainlist

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
    val weatherList: MutableList<WeatherData> = mutableListOf<WeatherData>()
    val showShimmerView: MutableLiveData<Boolean> = MutableLiveData(true)
    val showErrorView: MutableLiveData<Boolean> = MutableLiveData(false)
    val cityName: MutableLiveData<String> = MutableLiveData("")

    fun getTodayWeather() {
        showShimmerView.postValue(true)
        weatherList.clear()
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) { dataRepository.getTodayWeather() }
            when (result) {
                is UseCaseResult.Success -> {
                    showShimmerView.postValue(false)
                    showErrorView.postValue(false)

                    weatherList.clear()
                    weatherList.add(result.data)
                    cityName.postValue(result.data.name)
                }
                is UseCaseResult.Error -> {
                    showShimmerView.postValue(false)
                    showErrorView.postValue(true)
                }
            }
        }
    }

    fun getWeatherForecast() {
        showShimmerView.postValue(true)
        weatherList.clear()
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) { dataRepository.getWeatherForecast() }
            showShimmerView.postValue(false)
            when (result) {
                is UseCaseResult.Success -> {

                    result.data.list?.let {
                        showShimmerView.postValue(false)
                        showErrorView.postValue(false)

                        weatherList.clear()
                        weatherList.addAll(it)
                    }

                    result.data.city?.let{
                         cityName.postValue(it.name)
                    }
                }
                is UseCaseResult.Error -> {
                    showErrorView.postValue(true)
                    showShimmerView.postValue(false)
                }
            }
        }
    }

    fun reloadList() {
        if (weatherList.isNullOrEmpty()) {
            showErrorView.postValue(false)
            showShimmerView.postValue(true)
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