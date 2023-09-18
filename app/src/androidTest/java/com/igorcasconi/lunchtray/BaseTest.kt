package com.igorcasconi.lunchtray

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId

open class BaseTest {

    fun fullOrderFlow() {
        // Launch the main activity
        launchActivity<MainActivity>()
        // Start order
        onView(withId(R.id.button_start_order)).perform(click())
        // Select entree item
        onView(withId(R.id.option_entree_1)).perform(click())
        // Move to next fragment
        onView(withId(R.id.button_next)).perform(click())
        // Select side item
        onView(withId(R.id.option_side_dish_1)).perform(click())
        // Move to next fragment
        onView(withId(R.id.button_next)).perform(click())
        // Select accompaniment item
        onView(withId(R.id.option_side_accompaniment_1)).perform(click())
        // Move to next fragment
        onView(withId(R.id.button_next)).perform(click())
    }
}