package com.exercise.weatherapp.ui.mainlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.exercise.weatherapp.R
import com.exercise.weatherapp.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val todoListModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainBinding.viewmodel = todoListModel
        mainBinding.lifecycleOwner = this
        mainBinding.executePendingBindings()

        todoListModel.getWeatherForecast()
    }
}
