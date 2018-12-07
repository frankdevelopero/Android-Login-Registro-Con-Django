package com.frankdeveloper.logindjango.accesos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.frankdeveloper.logindjango.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        listenOnClick()

    }

    private fun listenOnClick() {
        btnGoLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
