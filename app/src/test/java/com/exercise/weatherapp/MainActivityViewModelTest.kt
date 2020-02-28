package com.exercise.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.exercise.weatherapp.data.repository.IDataRepository
import com.exercise.weatherapp.ui.mainlist.MainActivityViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainActivityViewModelTest {
    @Rule val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mainActivityViewModel: MainActivityViewModel
    @Mock
    private lateinit var dataRepository: IDataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(MainActivityViewModel::class);

        mainActivityViewModel = MainActivityViewModel(dataRepository = dataRepository)
    }

    @Test
    fun testGetTodayWeather() {
        mainActivityViewModel.getTodayWeather()
    }
}