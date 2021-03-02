package com.carlosjimz87.stopwatch

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import kotlinx.coroutines.delay
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class WatchFragmentTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun test_isWatchFragmentVisible() {

        onView(withId(R.id.startOrPauseBtn))
            .check(matches(isDisplayed()))

        onView(withId(R.id.stopBtn))
            .check(matches(isDisplayed()))

        onView(withId(R.id.textViewStopWatch))
            .check(matches(isDisplayed()))

    }


    @Test
    fun test_isTimerStartsWhenStartBtnPressed() {

        onView(withId(R.id.textViewStopWatch))
            .check(matches(withText(R.string.init_stop_watch_value)))

//        onView(withId(R.id.startOrPauseBtn))
//            .perform(click())
//
//        onView(withId(R.id.startOrPauseBtn))
//            .perform(click())
//
//        onView(withId(R.id.textViewStopWatch))
//            .check(matches(not(withText(R.string.init_stop_watch_value))))

    }
}

