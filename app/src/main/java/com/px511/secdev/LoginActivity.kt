package com.px511.secdev

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.*
import java.util.Random

class LoginActivity : AppCompatActivity() {

    val myDB = DBHelper(this)

    override fun onStart() {
        super.onStart()

        val sessionManagement = SessionManagement(this)
        val UserEmail = sessionManagement.session

        if (!UserEmail.equals("None")){
            val intent: Intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        //init of DB for tests data
        myDB.insertData("admin@admin.com", "admin123")
        myDB.insertData("test@test.com", "test123")
        myDB.insertData("test1@test.com","test123")

        email_sign_in_button.setOnClickListener { attemptLogin() }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptLogin() {
        // Reset errors.
        email.error = null
        password.error = null

        // Store values at the time of the login attempt.
        val emailStr = email.text.toString()
        val passwordStr = password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            password.error = getString(R.string.error_invalid_password)
            focusView = password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            email.error = getString(R.string.error_field_required)
            focusView = email
            cancel = true
        } else if (!isEmailValid(emailStr)) {
            email.error = getString(R.string.error_invalid_email)
            focusView = email
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            if (myDB.checkusername(emailStr)){
                if(myDB.checkusernamepassword(emailStr,passwordStr)) {
                    val user = User(emailStr, passwordStr)
                    val sessionManagement = SessionManagement(this)
                    sessionManagement.saveSession(user)

                    val intent: Intent = Intent(applicationContext, HomeActivity::class.java)
                    startActivity(intent)
                }
                else{
                    password.error = "Wrong password"
                    focusView = password
                    cancel = true
                }
            }
            else {
                email.error = "Wrong email"
                focusView = email
                cancel = true
            }

        }
    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 6
    }
}