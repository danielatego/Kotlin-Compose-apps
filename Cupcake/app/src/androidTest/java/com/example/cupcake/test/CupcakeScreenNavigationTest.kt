package com.example.cupcake.test

import android.icu.util.Calendar
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.cupcake.CupcakeApp
import com.example.cupcake.CupcakeScreen
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Locale

class CupcakeScreenNavigationTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CupcakeApp(navController = navController)
        }
    }
    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        //assertEquals(CupcakeScreen.Start.name, navController.currentBackStackEntry?.destination?.route)
        //shortened to
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }
    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen(){
        val backText = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }
    @Test
    fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen(){
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.one_cupcake).performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    private fun navigateToFlavorScreen() {
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.one_cupcake)
            .performClick()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.chocolate)
            .performClick()
    }
    @Test
    fun cupCakeNavHost_clickOneCupcakeSelectChocolateFlavourSelectNext_navigatestoSelectPickUpDate() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
    }
    private fun getFormattedDate(): String{
        val calendar = Calendar.getInstance()
        calendar.add(java.util.Calendar.DATE,1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }
    private fun navigateToPickupScreen() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
            .performClick()
    }
    private fun navigateToSummaryScreen() {
        navigateToPickupScreen()
        composeTestRule.onNodeWithText(getFormattedDate())
            .performClick()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next)
            .performClick()
    }
    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }

    private fun performCancel() {
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.cancel).performClick()
    }

    private fun performNext() {
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next).performClick()
    }

    @Test
    fun cupcakeNavHost_clickUpButtonOnFlavorScreen_navigatesToStartScreen(){
        navigateToFlavorScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }
    @Test
    fun cupcakeNavHost_clickCancelButtonFromFlavorScreen_navigatesToStartScreen(){
        navigateToFlavorScreen()
        performCancel()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }
    @Test
    fun cupcakeNaveHost_navigateToPickupScreen(){
        navigateToPickupScreen()
        navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
    }
    @Test
    fun cupcakeNavHost_clickUpButtonFromPickUpScreen_navigatesToFlavorScreen(){
        navigateToPickupScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }
    @Test
    fun cupcakeNavHost_ClickCancelFromPickUpScreen_navigatesToStartScreen(){
        navigateToPickupScreen()
        performCancel()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }
    @Test
    fun cupCakenavHost_navigatingToSummaryScreen(){
        navigateToSummaryScreen()
        navController.assertCurrentRouteName(CupcakeScreen.Summary.name)

    }
    @Test
    fun cupcakeNavHost_ClickCancelFromSummaryScreen_navigatesToStartScreen(){
        navigateToSummaryScreen()
        performCancel()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }
}