package com.igorcasconi.lunchtray

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.igorcasconi.lunchtray.ui.AccompanimentDish
import com.igorcasconi.lunchtray.ui.EntreeOrderFragment
import com.igorcasconi.lunchtray.ui.SideDish
import org.hamcrest.CoreMatchers.containsString
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class OrderFunctionalityTests: BaseTest() {
    /**
     * Test subtotal in [EntreeOrderFragment]
     *
     * It isn't necessarily best practice to make all these assertions in a single test,
     * however, it is done here for improved readability of the file.
     */
    @Test
    fun `radio_buttons_update_entree_menu_subtotal`() {
        // Launch the entree menu fragment
        launchFragmentInContainer<EntreeOrderFragment>(themeResId = R.style.Theme_LunchTray)

        // Select the cauliflower item
        onView(withId(R.id.option_entree_1)).perform(click())
        onView(withId(R.id.text_subtotal))
            .check(matches(withText(containsString("Subtotal $7.00"))))

        // Select the chili item
        onView(withId(R.id.option_entree_2)).perform(click())
        onView(withId(R.id.text_subtotal))
            .check(matches(withText(containsString("Subtotal $4.00"))))

        // Select the pasta item
        onView(withId(R.id.option_entree_3)).perform(click())
        onView(withId(R.id.text_subtotal))
            .check(matches(withText(containsString("Subtotal $5.50"))))

        // Select the skillet item
        onView(withId(R.id.option_entree_4)).perform(click())
        onView(withId(R.id.text_subtotal))
            .check(matches(withText(containsString("Subtotal $5.50"))))
    }

    /**
     * Test subtotal in [SideMenuFragment]
     *
     * It isn't necessarily best practice to make all these assertions in a single test,
     * however, it is done here for improved readability of the file.
     */
    @Test
    fun `radio_buttons_update_side_menu_subtotal`() {
        // Launch the side menu fragment
        launchFragmentInContainer<SideDish>(themeResId = R.style.Theme_LunchTray)

        // Select the salad item
        onView(withId(R.id.option_side_dish_1)).perform(click())
        onView(withId(R.id.text_subtotal))
            .check(matches(withText(containsString("Subtotal $2.50"))))

        // Select the soup item
        onView(withId(R.id.option_side_dish_2)).perform(click())
        onView(withId(R.id.text_subtotal))
            .check(matches(withText(containsString("Subtotal $3.00"))))

        // Select the potato item
        onView(withId(R.id.option_side_dish_3)).perform(click())
        onView(withId(R.id.text_subtotal))
            .check(matches(withText(containsString("Subtotal $2.00"))))

        // Select the rice item
        onView(withId(R.id.option_side_dish_4)).perform(click())
        onView(withId(R.id.text_subtotal))
            .check(matches(withText(containsString("Subtotal $1.50"))))
    }

    /**
     * Test subtotal in [AccompanimentMenuFragment]
     *
     * It isn't necessarily best practice to make all these assertions in a single test,
     * however, it is done here for improved readability of the file.
     */
    @Test
    fun `radio_buttons_update_accompaniment_menu_subtotal`() {
        // Launch the side menu fragment
        launchFragmentInContainer<AccompanimentDish>(themeResId = R.style.Theme_LunchTray)

        // Select the salad item
        onView(withId(R.id.option_side_accompaniment_1)).perform(click())
        onView(withId(R.id.text_subtotal))
            .check(matches(withText(containsString("Subtotal $0.50"))))

        // Select the soup item
        onView(withId(R.id.option_side_accompaniment_2)).perform(click())
        onView(withId(R.id.text_subtotal))
            .check(matches(withText(containsString("Subtotal $1.00"))))

        // Select the potato item
        onView(withId(R.id.option_side_accompaniment_3)).perform(click())
        onView(withId(R.id.text_subtotal))
            .check(matches(withText(containsString("Subtotal $0.50"))))
    }

    /**
     * Test subtotals in full order flow
     */
    @Test
    fun `subtotal_updates_in_full_order_flow`() {
        // Launch the main activity
        launchActivity<MainActivity>()
        // Start order
        onView(withId(R.id.button_start_order)).perform(click())
        // Select entree item
        onView(withId(R.id.option_entree_1)).perform(click())
        // We already have a test for a single menu item selection, so we don't need to check the
        // subtotal here.
        // Move to next fragment
        onView(withId(R.id.button_next)).perform(click())
        // Select side item
        onView(withId(R.id.option_side_dish_1)).perform(click())
        // Check that subtotal has updated
        onView(withId(R.id.text_subtotal))
            .check(matches(withText(containsString("Subtotal $9.50"))))
        // Move to next fragment
        onView(withId(R.id.button_next)).perform(click())
        // Select accompaniment item
        onView(withId(R.id.option_side_accompaniment_1)).perform(click())
        // Check that subtotal has updated
        onView(withId(R.id.text_subtotal))
            .check(matches(withText(containsString("Subtotal $10.00"))))
        // Move to next fragment
        onView(withId(R.id.button_next)).perform(click())
        // Check that subtotal in checkout is correct
        onView(withId(R.id.subtotal_order))
            .check(matches(withText(containsString("Subtotal: $10.00"))))
    }

    /**
     * Test subtotal, tax, and total in [SummaryOrder]
     */
    @Test
    fun `subtotal_tax_total_in_checkout`() {
        // Select items and move to checkout
        fullOrderFlow()
        // Check subtotal. Note that this is already done in a separate test, but the other values
        // depend on it so this assertion is a sanity check
        onView(withId(R.id.subtotal_order))
            .check(matches(withText(containsString("Subtotal: $10.00"))))
        // Check tax
        onView(withId(R.id.tax_order))
            .check(matches(withText(containsString("Tax: $0.80"))))
        // Check total
        onView(withId(R.id.total_order))
            .check(matches(withText(containsString("TOTAL ORDER: $10.80"))))
    }

    /**
     * Test that the order is reset after canceling in [EntreeOrderFragment]
     */
    @Test
    fun `order_reset_after_cancel_from_entree_menu`() {
        // Launch the app
        launchActivity<MainActivity>()
        // Start the order
        onView(withId(R.id.button_start_order)).perform(click())
        // Select an item
        onView(withId(R.id.option_entree_1)).perform(click())
        // Cancel order
        onView(withId(R.id.button_cancel)).perform(click())
        // Start the order
        onView(withId(R.id.button_start_order)).perform(click())
        // Make sure subtotal is zero
        onView(withText("Subtotal $0.00")).check(matches(isDisplayed()))
    }

    /**
     * Test that the order is reset after canceling in [SideDish]
     */
    @Test
    fun `order_reset_after_cancel_from_side_menu`() {
        // Launch the app
        launchActivity<MainActivity>()
        // Start the order
        onView(withId(R.id.button_start_order)).perform(click())
        // Select an item
        onView(withId(R.id.option_entree_1)).perform(click())
        // Move to side menu
        onView(withId(R.id.button_next)).perform(click())
        // Select an item
        onView(withId(R.id.option_side_dish_2)).perform(click())
        // Cancel the order
        onView(withId(R.id.button_cancel)).perform(click())
        // Start the order
        onView(withId(R.id.button_start_order)).perform(click())
        // Make sure subtotal is zero
        onView(withText("Subtotal $0.00")).check(matches(isDisplayed()))
    }

    /**
     * Test that the order is reset after canceling in [AccompanimentDish]
     */
    @Test
    fun `order_reset_after_cancel_from_accompaniment_menu`() {
        // Launch the app
        launchActivity<MainActivity>()
        // Start the order
        onView(withId(R.id.button_start_order)).perform(click())
        // Select an item
        onView(withId(R.id.option_entree_1)).perform(click())
        // Move to side menu
        onView(withId(R.id.button_next)).perform(click())
        // Select an item
        onView(withId(R.id.option_side_dish_2)).perform(click())
        // Move to accompaniment menu
        onView(withId(R.id.button_next)).perform(click())
        // Select item
        onView(withId(R.id.option_side_accompaniment_1)).perform(click())
        // Cancel the order
        onView(withId(R.id.button_cancel)).perform(click())
        // Start the order
        onView(withId(R.id.button_start_order)).perform(click())
        // Make sure subtotal is zero
        onView(withText("Subtotal $0.00")).check(matches(isDisplayed()))
    }

    /**
     * Test that the order is reset after canceling in [SummaryOrder]
     */
    @Test
    fun `order_reset_after_cancel_from_checkout`() {
        // Select items and move to checkout
        fullOrderFlow()
        // Cancel the order
        onView(withId(R.id.button_cancel)).perform(click())
        // Start the order
        onView(withId(R.id.button_start_order)).perform(click())
        // Make sure subtotal is zero
        onView(withText("Subtotal $0.00")).check(matches(isDisplayed()))
    }

    /**
     * Test that the correct snackbar is displayed when order is submitted
     */
    @Test
    fun `order_snackbar`() {
        // Select items and move to checkout
        fullOrderFlow()
        // Click submit
        onView(withId(R.id.button_submit)).perform(click())
        // Check for snackbar text
        onView(withText(containsString("Order Submitted!")))
            .check(matches(isDisplayed()))
    }
}