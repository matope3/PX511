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
        robot.restartActivity(activityRule)
        robot.checkSession()
    }

    @And("^après la déconnexion, la session devrait être invalidée$")
    fun apresLaDeconnexionLaSessionDevraitEtreInvalidee() {
        robot.logout()
        robot.checkLoginPage()
    }
}