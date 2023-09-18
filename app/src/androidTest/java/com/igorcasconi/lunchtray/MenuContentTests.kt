package com.igorcasconi.lunchtray

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.igorcasconi.lunchtray.ui.AccompanimentDish
import com.igorcasconi.lunchtray.ui.EntreeOrderFragment
import com.igorcasconi.lunchtray.ui.SideDish
import org.hamcrest.CoreMatchers.containsString
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class MenuContentTests : BaseTest() {

    /**
     * Test the menu content of the entire [EntreeOrderFragment]
     *
     * It isn't necessarily best practice to make all these assertions in a single test,
     * however, it is done here for improved readability of the file.
     */
    @Test
    fun `entree_menu_item_content`() {
        // launch the entree menu fragment
        launchFragmentInContainer<EntreeOrderFragment>(themeResId = R.style.Theme_LunchTray)

        // Check the cauliflower item
        onView(withId(R.id.option_entree_1))
            .check(matches(withText(containsString("Cauliflower"))))
        onView(withId(R.id.option_entree_1_description))
            .check(matches(withText(containsString("Whole cauliflower"))))
        onView(withId(R.id.option_entree_1_price))
            .check(matches(withText(containsString("$ 7.00"))))

        // Check the chili item
        onView(withId(R.id.option_entree_2))
            .check(matches(withText(containsString("Three Bean Chili"))))
        onView(withId(R.id.option_entree_2_description))
            .check(matches(withText(containsString("Black beans"))))
        onView(withId(R.id.option_entree_2_price))
            .check(matches(withText(containsString("$ 4.00"))))

        // Check the pasta item
        onView(withId(R.id.option_entree_3))
            .check(matches(withText(containsString("Mushroom Pasta"))))
        onView(withId(R.id.option_entree_3_description))
            .check(matches(withText(containsString("Penne pasta"))))
        onView(withId(R.id.option_entree_3_price))
            .check(matches(withText(containsString("$ 5.50"))))

        // Check the skillet item
        onView(withId(R.id.option_entree_4))
            .check(matches(withText(containsString("Spicy Black Bean"))))
        onView(withId(R.id.option_entree_4_description))
            .check(matches(withText(containsString("Seasonal vegetables"))))
        onView(withId(R.id.option_entree_4_price))
            .check(matches(withText(containsString("$ 5.50"))))
    }

    /**
     * Test the menu content of the entire [SideDish]
     *
     * It isn't necessarily best practice to make all these assertions in a single test,
     * however, it is done here for improved readability of the file by reducing the number of
     * functions that would otherwise be necessary to test each item separately.
     */
    @Test
    fun `side_menu_item_content`() {
        // launch the side menu fragment
        launchFragmentInContainer<SideDish>(themeResId = R.style.Theme_LunchTray)

        // Check the salad item
        onView(withId(R.id.option_side_dish_1))
            .check(matches(withText(containsString("Summer Salad"))))
        onView(withId(R.id.option_side_dish_1_description))
            .check(matches(withText(containsString("Heirloom tomatoes"))))
        onView(withId(R.id.option_side_dish_1_price))
            .check(matches(withText(containsString("$ 2.50"))))

        // Check the soup item
        onView(withId(R.id.option_side_dish_2))
            .check(matches(withText(containsString("Butternut Squash"))))
        onView(withId(R.id.option_side_dish_2_description))
            .check(matches(withText(containsString("Roasted butternut squash"))))
        onView(withId(R.id.option_side_dish_2_price))
            .check(matches(withText(containsString("$ 3.00"))))

        // Check the potato item
        onView(withId(R.id.option_side_dish_3))
            .check(matches(withText(containsString("Spicy Potatoes"))))
        onView(withId(R.id.option_side_dish_3_description))
            .check(matches(withText(containsString("Marble potatoes"))))
        onView(withId(R.id.option_side_dish_3_price))
            .check(matches(withText(containsString("$ 2.00"))))

        // Check the rice item
        onView(withId(R.id.option_side_dish_4))
            .check(matches(withText(containsString("Coconut Rice"))))
        onView(withId(R.id.option_side_dish_4_description))
            .check(matches(withText(containsString("Rice, coconut milk"))))
        onView(withId(R.id.option_side_dish_4_price))
            .check(matches(withText(containsString("$ 1.50"))))
    }

    /**
     * Test the menu content of the entire [AccompanimentDish]
     *
     * It isn't necessarily best practice to make all these assertions in a single test,
     * however, it is done here for improved readability of the file by reducing the number of
     * functions that would otherwise be necessary to test each item separately.
     */
    @Test
    fun `accompaniment_menu_item_content`() {
        // launch the accompaniment menu fragment
        launchFragmentInContainer<AccompanimentDish>(themeResId = R.style.Theme_LunchTray)

        // Check the bread item
        onView(withId(R.id.option_side_accompaniment_1))
            .check(matches(withText(containsString("Lunch Roll"))))
        onView(withId(R.id.option_side_accompaniment_1_description))
            .check(matches(withText(containsString("Fresh baked"))))
        onView(withId(R.id.option_side_accompaniment_1_price))
            .check(matches(withText(containsString("$ 0.50"))))

        // Check the berries item
        onView(withId(R.id.option_side_accompaniment_2))
            .check(matches(withText(containsString("Mixed Berries"))))
        onView(withId(R.id.option_side_accompaniment_2_description))
            .check(matches(withText(containsString("Strawberries"))))
        onView(withId(R.id.option_side_accompaniment_2_price))
            .check(matches(withText(containsString("$ 1.00"))))

        // Check the pickle item
        onView(withId(R.id.option_side_accompaniment_3))
            .check(matches(withText(containsString("Pickled Veggies"))))
        onView(withId(R.id.option_side_accompaniment_3_description))
            .check(matches(withText(containsString("Pickled cucumbers"))))
        onView(withId(R.id.option_side_accompaniment_3_price))
            .check(matches(withText(containsString("$ 0.50"))))
    }
}