package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest<OrderViewModel> {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        // Given list of options
        val flavors = listOf("vanilla","Chocolate","Hazelnut","Cookie","Mango")
        // And subtotal
        val subtotal = "$100"

        // When SelectOptionScreen is loaded
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal,options = flavors)
        }

        // Then all the options are displayed on the screen.
        flavors.forEach{flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        // And the the subtotal is displayed correctly.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(com.example.cupcake.R.string.subtotal_price,subtotal)
        ).assertIsDisplayed()

        // And then the next button is disabled
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next).assertIsNotEnabled()

        //Clicks one of the options and then confirms that the next button is enabled
        composeTestRule.onNodeWithText(flavors[1]).performClick()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next).assertIsEnabled()

    }

    @Test
    fun selectStartScreen_verifyContent(){
        val quantityOptions  = listOf(
            Pair(com.example.cupcake.R.string.one_cupcake,1),
            Pair(com.example.cupcake.R.string.six_cupcakes,6),
            Pair(com.example.cupcake.R.string.twelve_cupcakes,12)
        )
        composeTestRule.setContent {
            StartOrderScreen(quantityOptions)
        }
        // Test whether the 'order cupcakes' title is displayed
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.order_cupcakes).assertIsDisplayed()

        // Test whether there are three buttons
        quantityOptions.forEach {
            composeTestRule.onNodeWithStringId(it.first).assertExists()
        }

    }


    @Test
    fun selectSummaryScreen_viewContent(){
        composeTestRule.setContent {
            OrderSummaryScreen(OrderUiState(0, "Caramel", getFormattedDate(), "$300.00"),{},{ _: String, _: String ->})
        }
        confirmNodeExists(com.example.cupcake.R.string.quantity)
        confirmNodeExists(com.example.cupcake.R.string.flavor)
        confirmNodeExists(com.example.cupcake.R.string.pickup_date)
        confirmTextExists("0 cupcakes")
        confirmTextExists(getFormattedDate())
        confirmTextExists("Subtotal $300.00")
        confirmNodeExists(com.example.cupcake.R.string.send)
        confirmNodeExists(com.example.cupcake.R.string.send)






    }

    private fun confirmNodeExists(node:Int){
        composeTestRule.onNodeWithStringId(node).assertExists()
    }
    private fun confirmTextExists(text: String){
        composeTestRule.onNodeWithText(text).assertExists()
    }



}