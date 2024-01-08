package com.px511.secdev

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class HomeActivity : AppCompatActivity() {

    private lateinit var logout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        logout = findViewById(R.id.logout)

        logout.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                // val sessionManagement = SessionManagement(this@HomeActivity)
                // sessionManagement.removeSession()

                val intent: Intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
