package com.sniper.bdd

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class LoginActivity : AppCompatActivity() {

    // Init de la BDD
    private lateinit var myDB: DBHelper

    // Init des préférences (pour garder en mémoire le blocage de login)
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        myDB = DBHelper(this)
        prefs = getSharedPreferences("LoginTrack", MODE_PRIVATE)

        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        myDB.insertData("admin@admin.com", "admin");
        myDB.insertData("user@user.com", "user");
        myDB.insertData("test@test.com", "test");

        email_sign_in_button.isEnabled = loginEnabled()
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
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(emailStr,passwordStr)) {
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
            loginFailed()
            focusView?.requestFocus()
        } else {
            loginSuccess()
            successful_login_text_view.visibility = View.VISIBLE
        }

        email_sign_in_button.isEnabled = loginEnabled()
    }

    private fun isEmailValid(email: String): Boolean {
        return myDB.checkusername(email);
    }

    private fun isPasswordValid(email: String,password: String): Boolean {
        return myDB.checkusernamepassword(email,password);
    }

    private fun loginEnabled(): Boolean {
        val LastAttempt: Long = prefs.getLong("LastLoginDateTime", Date().getTime())
        val Attempts = prefs.getString("MaxAttempts", "")

        if (Attempts != null && Attempts.isNotEmpty()) {
            if (Attempts.toInt()>=3) {
                if (LastAttempt > Date().time - 60000) {
                    return false
                } else {
                    prefs.edit().putString("MaxAttempts", "0").apply()
                    return true
                }
            } else {
                return true
            }
        } else {
            prefs.edit().putString("MaxAttempts", "0").apply()
            return true
        }
    }

    private fun loginFailed() {
        val Attempts = prefs.getString("MaxAttempts", "")
        if (Attempts != null) {
            prefs.edit().putString("MaxAttempts", (Attempts.toInt()+1).toString()).apply()
        }
        prefs.edit().putLong("LastLoginDateTime", Date().getTime()).apply()
    }

    private fun loginSuccess() {
        prefs.edit().putString("MaxAttempts", "0").apply()
    }

}
