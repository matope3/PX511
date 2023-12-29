package com.px511.secdev.cucumber.espresso.login

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.px511.secdev.LoginActivity
import com.px511.secdev.R
import java.lang.Thread.sleep

class LoginScreenRobot {

    fun launchLoginScreen(testRule: ActivityTestRule<LoginActivity>) {
        testRule.launchActivity(null)
    }

    fun selectEmailField() {
        onView(withId(R.id.email)).perform(scrollTo(),  click())
    }

    fun selectPasswordField() {
        onView(withId(R.id.password)).perform(scrollTo(),  click())
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
        onView(withId(R.id.email_sign_in_button)).perform(scrollTo(),  click())
    }

    fun isSuccessfulLogin() {
        onView(withId(R.id.successful_login_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.successful_login_text_view)).check(matches(withText(R.string.successful_login)))
    }

    fun isDeniedLogin() {
        onView(withId(R.id.unsuccessful_login_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.unsuccessful_login_text_view)).check(matches(withText(R.string.unsuccessful_login)))
    }

}