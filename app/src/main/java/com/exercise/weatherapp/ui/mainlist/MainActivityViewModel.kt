package com.exercise.weatherapp.ui.mainlist

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
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

    //override val coroutineContext: CoroutineContext = Dispatchers.Main + job
    // Q: What is withContext, Scope?, suspend/resume?
    fun getTodayWeather() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) { dataRepository.getTodayWeather() }
            //showShimmerView.set(false)
            when (result) {
                is UseCaseResult.Success -> {
                    weatherList.clear()
                    weatherList.add(result.data)
                    showErrorView.set(false)
                }
                is UseCaseResult.Error -> {
                    showErrorView.set(true)
                }
            }
        }
        //Q: async and usage for coroutine?
        // Q: println("")
    }

    fun getWeatherForecast() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) { dataRepository.getWeatherForecast() }
            //showShimmerView.set(false)
            when (result) {
                is UseCaseResult.Success -> {
                    weatherList.clear()
                    //weatherList.addAll(result.data.list)
                    showErrorView.set(false)
                }
                is UseCaseResult.Error -> {
                    showErrorView.set(true)
                }
            }
        }
        //Q: async and usage for coroutine?
        // Q: println("")
    }

    fun reloadList() {
        if (weatherList.isNullOrEmpty()) {
            showErrorView.set(false)
            showShimmerView.set(true)
            getTodayWeather()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    /**
     * The Single item.

    val accessoryBinding = ItemBinding.of<ListItem> { itemBinding, _, _ ->
        itemBinding.set(BR.itemViewModel, R.layout.layout_item_todo_list)
    }*/
}