package com.lumisdinos.mindicador.ui.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class MainActivityTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activityScenario = launch(MainActivity::class.java)
    }

    @Ignore()
    @Test
    fun whenPressBackToolbarButton_thenFinishActivity() {
        Espresso.onView(ViewMatchers.withId(android.R.id.home))
            .perform(ViewActions.click())
              //assertTrue(activityRule.activity.isFinishing)
    }
}