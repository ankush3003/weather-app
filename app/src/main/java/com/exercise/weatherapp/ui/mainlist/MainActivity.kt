package com.exercise.weatherapp.ui.mainlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.exercise.weatherapp.R
import com.exercise.weatherapp.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val todoListModel: MainActivityViewModel by viewModel()
    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.viewmodel = todoListModel

        todoListModel.getWeatherForecast()
    }
}
