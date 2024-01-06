package com.sniper.bdd.cucumber.steps

import android.support.test.rule.ActivityTestRule
import com.sniper.bdd.LoginActivity
import com.sniper.bdd.cucumber.espresso.login.LoginScreenRobot
import com.sniper.bdd.utils.ActivityFinisher
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

class LoginDetailsSteps {

    private val robot = LoginScreenRobot()

    private val activityRule = ActivityTestRule(LoginActivity::class.java, false, false)

    @Before
    fun setup() {
        //not needed now, but you may needed to setup mock responses before your screen starts
    }

    @After
    fun tearDown() {
        ActivityFinisher.finishOpenActivities() // Required for test scenarios with multiple activities or scenarios with more cases
    }

    @Given("^I start the application$")
    fun i_start_app() {
        robot.launchLoginScreen(activityRule)
    }

    @When("^I try to logging ([0-9]*) times$")
    fun iTryToLoggingNumberTimes(number: Int) {

            robot.selectEmailField()
            robot.enterEmail("brute@force.com")
            robot.selectPasswordField()
            robot.enterPassword("bruteforce")
            robot.closeKeyboard()
        for (i in 1..number) {
            robot.clickSignInButton()
        }
    }

    @Then("^I'm waiting for the login button to be blocked$")
    fun iMWaitingForTheLoginButtonToBeBlocked() {
        robot.checkIfSignInButtonIsBlocked()
    }
}
