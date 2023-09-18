package com.igorcasconi.lunchtray
/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.view.WindowInsets.Side
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.igorcasconi.lunchtray.ui.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests for all navigation flows
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class NavigationTests : BaseTest() {

    /**
     * Test navigation from [StartOrder] to [EntreeOrderFragment]
     */
    @Test
    fun `navigate_to_entree_menu_from_start_order`() {
        // Init nav controller
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        // Launch StartOrderFragment
        val startOrderScenario =
            launchFragmentInContainer<StartOrder>(themeResId = R.style.Theme_LunchTray)
        // Configure nav controller
        startOrderScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        // Click start order
        onView(withId(R.id.button_start_order)).perform(click())
        // Check destination is correct
        assertEquals(navController.currentDestination?.id, R.id.entreeOrderFragment)
    }

    /**
     * Test navigation from [EntreeOrderFragment] to [StartOrder]
     */
    @Test
    fun `navigate_to_start_order_from_entree_menu`() {
        // Init nav controller
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        // Launch EntreeMenuFragment
        val entreeMenuScenario =
            launchFragmentInContainer<EntreeOrderFragment>(themeResId = R.style.Theme_LunchTray)
        // Configure nav controller
        entreeMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            // Destination defaults to the home fragment, we have to explicitly set the current
            // destination
            navController.setCurrentDestination(destId = R.id.entreeOrderFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        // Click the cancel button
        onView(withId(R.id.button_cancel)).perform(click())
        // Check that the destination is correct
        assertEquals(navController.currentDestination?.id, R.id.startOrder)
    }

    /**
     * Test navigation from [EntreeOrderFragment] to [SideDish]
     */
    @Test
    fun `navigate_to_side_menu_from_entree_menu`() {
        // Init nav controller
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        // Launch the EntreeMenuFragment
        val entreeMenuScenario =
            launchFragmentInContainer<EntreeOrderFragment>(themeResId = R.style.Theme_LunchTray)
        // Configure nav controller
        entreeMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            // Destination defaults to the home fragment, we have to explicitly set the current
            // destination
            navController.setCurrentDestination(destId = R.id.entreeOrderFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        // Click the next button
        onView(withId(R.id.button_next)).perform(click())
        // Check that the destination is correct
        assertEquals(navController.currentDestination?.id, R.id.sideDish)
    }

    /**
     * Test navigation from [SideDish] to [StartOrder]
     */
    @Test
    fun `navigate_to_start_order_from_side_menu`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val sideMenuScenario =
            launchFragmentInContainer<SideDish>(themeResId = R.style.Theme_LunchTray)
        sideMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.sideDish)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.button_cancel)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startOrder)
    }

    /**
     * Test navigation from [SideDish] to [AccompanimentDish]
     */
    @Test
    fun `navigate_to_accompaniment_menu_from_side_menu`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val sideMenuScenario =
            launchFragmentInContainer<SideDish>(themeResId = R.style.Theme_LunchTray)
        sideMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.sideDish)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.button_next)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.accompanimentDish)
    }

    /**
     * Test navigation from [AccompanimentDish] to [StartOrder]
     */
    @Test
    fun `navigate_to_start_order_from_accompaniment_menu`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val accompanimentMenuScenario =
            launchFragmentInContainer<AccompanimentDish>(
                themeResId = R.style.Theme_LunchTray)
        accompanimentMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.accompanimentDish)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.button_cancel)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startOrder)
    }

    /**
     * Test navigation from [AccompanimentDish] to [SummaryOrder]
     */
    @Test
    fun `navigate_to_checkout_from_accompaniment_menu`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val accompanimentMenuScenario =
            launchFragmentInContainer<AccompanimentDish>(
                themeResId = R.style.Theme_LunchTray)
        accompanimentMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.accompanimentDish)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.button_next)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.summaryOrder)
    }

    /**
     * Test navigation from [SummaryOrder] to [StartOrder]
     */
    @Test
    fun `navigate_to_start_order_from_checkout`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val checkoutScenario =
            launchFragmentInContainer<SummaryOrder>(themeResId = R.style.Theme_LunchTray)
        checkoutScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.summaryOrder)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.button_cancel)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startOrder)
    }

    /**
     * Test Navigation from [SummaryOrder] to [StartOrder]
     */
    @Test
    fun `navigate_to_start_order_from_checkout_via_submit`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val checkoutScenario =
            launchFragmentInContainer<SummaryOrder>(themeResId = R.style.Theme_LunchTray)
        checkoutScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.summaryOrder)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.button_submit)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startOrder)
    }
}