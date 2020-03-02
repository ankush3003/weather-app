package com.exercise.weatherapp;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MainActivityViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        viewModel = new NewsViewModel(apiClient, RxSingleSchedulers.TEST_SCHEDULER);
        viewModel.getNewsListState().observeForever(observer);
    }
}
