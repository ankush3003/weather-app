package com.exercise.weatherapp.ui.mainlist

import androidx.test.core.app.ActivityScenario
import org.junit.After
import org.junit.Before
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.exercise.weatherapp.R
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by ankush3003
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_isActivityInView() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }
}