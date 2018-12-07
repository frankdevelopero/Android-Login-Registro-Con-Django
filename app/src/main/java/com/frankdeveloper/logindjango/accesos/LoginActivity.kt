package com.frankdeveloper.logindjango.accesos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.frankdeveloper.logindjango.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        listenOnClick()
    }

    private fun listenOnClick() {
        btnGoRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }


    private fun login(){

    }
}
