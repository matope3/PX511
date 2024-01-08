package com.px511.secdev.cucumber.espresso.login

import android.content.SharedPreferences
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.px511.secdev.HomeActivity
import com.px511.secdev.LoginActivity
import com.px511.secdev.R
import com.px511.secdev.SessionManagement
import java.lang.Thread.sleep

class Robot {

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

    fun getSession() : String{
        val sessionManagement = SessionManagement(null)
        return sessionManagement.session
    }

    fun isSuccessfulLogin() {
        onView(withId(R.id.successful_login_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.successful_login_text_view)).check(matches(withText(R.string.successful_login)))
    }

    fun isDeniedLogin() {
        onView(withId(R.id.unsuccessful_login_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.unsuccessful_login_text_view)).check(matches(withText(R.string.unsuccessful_login)))
    }

    fun checkSession(){
        onView(withId(R.id.logout)).check(withCustomMessage("Session valid", matches(isDisplayed())))

        intended(hasComponent(HomeActivity::class.java.name))
    }

    fun checkLoginPage(){
        intended(hasComponent(LoginActivity::class.java.name))
    }

    fun logout(){
        onView(withId(R.id.logout)).perform(click())
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