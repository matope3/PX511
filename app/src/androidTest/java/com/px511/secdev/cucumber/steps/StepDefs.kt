package com.px511.secdev.cucumber.steps

import android.support.test.rule.ActivityTestRule
import com.px511.secdev.LoginActivity
import com.px511.secdev.cucumber.espresso.login.Robot
import com.px511.secdev.utils.ActivityFinisher
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

class StepDefs {

    private val robot = Robot()

    private val activityRule = ActivityTestRule(LoginActivity::class.java, false, false)

    @Before
    fun setup() {
        //not needed now, but you may need to set up mock responses before your screen starts
    }

    @After
    fun tearDown() {
        ActivityFinisher.finishOpenActivities() // Required for test scenarios with multiple activities or scenarios with more cases
    }

    @Given("^I start the application$")
    fun i_start_app() {
        robot.launchLoginScreen(activityRule)
    }

    @When("^I click on email field$")
    fun i_click_email_field() {
        robot.selectEmailField()
    }

    @And("^I close the keyboard$")
    fun i_close_keyboard() {
        robot.closeKeyboard()
    }

    @And("^I enter an email (.*)$")
    fun i_enter_email(email: String) {
        robot.enterEmail(email)
    }

    @And("^I click on password field$")
    fun i_click_password_field() {
        robot.selectPasswordField()
    }

    @And("^I enter a password (.*)$")
    fun i_enter_password(password: String) {
        robot.enterPassword(password)
    }

    @And("^I click on sign in button$")
    fun i_click_sign_in_button() {
        robot.clickSignInButton()
    }

    @Then("^I expect to see successful login message$")
    fun i_expect_to_see_successful_login_message() {
        robot.isSuccessfulLogin()
    }

    @Then("^I expect to deny login if SQL injection is (true|false) attempted$")
    fun iExpectToDenyLoginIfSQLInjection(attempted: Boolean) {
        if (attempted){
            robot.isDeniedLogin()
        }
        else {
            robot.isSuccessfulLogin()
        }
    }


    @Given("^que je suis connecté avec mon (.*) et (.*)$")
    fun queJeSuisConnecteAvecMonEmailEtPassword(email: String, password: String) {
        robot.launchLoginScreen(activityRule)
        robot.enterEmail(email)
        robot.enterPassword(password)
    }

    @When("^je me connecte à mon compte$")
    fun jeMeConnecteAMonCompte() {
        robot.clickSignInButton()
    }

    @Then("^une nouvelle session utilisateur devrait être créée$")
    fun uneNouvelleSessionUtilisateurDevraitEtreCreee() {
        robot.checkSession()
    }

    @And("^après la déconnexion, la session devrait être invalidée$")
    fun apresLaDeconnexionLaSessionDevraitEtreInvalidee() {
        robot.logout()
        robot.checkLoginPage()
    }
}