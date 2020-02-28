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
    /*val listOfTodo = ObservableArrayList<WeatherData>()
    val showShimmerView = ObservableBoolean(true)
    val showErrorView = ObservableBoolean(false)
*/
    //override val coroutineContext: CoroutineContext = Dispatchers.Main + job
    // Q: What is withContext, Scope?, suspend/resume?
    fun getTodoList() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) { dataRepository.getTodoList() }
            //showShimmerView.set(false)
            when (result) {
                is UseCaseResult.Success -> {
                    /*listOfTodo.clear()
                    listOfTodo.addAll(result.data)
                    showErrorView.set(false)*/
                }
                is UseCaseResult.Error -> {
                    //showErrorView.set(true)
                }
            }
        }
        //Q: async and usage for coroutine?
        // Q: println("")
    }

    fun reloadList() {
        /*if (listOfTodo.isNullOrEmpty()) {
            showErrorView.set(false)
            showShimmerView.set(true)
            getTodoList()
        }*/
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