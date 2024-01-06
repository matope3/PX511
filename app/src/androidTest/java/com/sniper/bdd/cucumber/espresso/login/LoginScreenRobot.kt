package com.sniper.bdd.cucumber.espresso.login

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.base.DefaultFailureHandler
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.not
import android.support.test.rule.ActivityTestRule
import com.sniper.bdd.LoginActivity
import com.sniper.bdd.R
import java.lang.Thread.sleep
import kotlin.Exception

class LoginScreenRobot {

    fun launchLoginScreen(testRule: ActivityTestRule<LoginActivity>) {
        testRule.launchActivity(null)
    }

    fun selectEmailField() {
        onView(withId(R.id.email)).perform(click())
    }

    fun selectPasswordField() {
        onView(withId(R.id.password)).perform(click())
    }

    fun enterEmail(email: String) {
        onView(withId(R.id.email)).perform(typeText(email))
    }

    fun enterPassword(password: String) {
        onView(withId(R.id.password)).perform(typeText(password))
    }

    fun closeKeyboard() {
        Espresso.closeSoftKeyboard()
        sleep(100)
    }

    fun clickSignInButton() {
        onView(withText(InstrumentationRegistry.getTargetContext().getString(R.string.action_sign_in))).perform(click())
    }

    fun checkIfSignInButtonIsBlocked() {
        onView(withId(R.id.email_sign_in_button)).check(withCustomMessage("Button is not blocked", matches(not(isEnabled()))));
    }

    private fun withCustomMessage(message: String, assertion: ViewAssertion): ViewAssertion {
        return ViewAssertion { view, noViewFoundException ->
            try {
                assertion.check(view, noViewFoundException)
            } catch (e: AssertionError) {
                throw AssertionError(message, e)
            }
        }
    }

}
