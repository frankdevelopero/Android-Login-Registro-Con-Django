package com.frankdeveloper.logindjango

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.frankdeveloper.logindjango.accesos.LoginActivity
import com.frankdeveloper.logindjango.modelos.UsuarioModelo
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (UsuarioModelo.instance.get_id(this) == 0){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        listenOnClick()
        showInfo()
    }

    private fun showInfo() {
        val fullname = UsuarioModelo.instance.get_nombre(this) +" "+UsuarioModelo.instance.get_apellido(this)
        txt_full_name.text = fullname
        txt_email.text = UsuarioModelo.instance.get_correo(this)
    }

    private fun listenOnClick() {
        btn_logout.setOnClickListener {
            logout()
        }
    }

    private fun logout(){
        UsuarioModelo.instance.del_id(this)
        UsuarioModelo.instance.del_nombre(this)
        UsuarioModelo.instance.del_apellido(this)
        UsuarioModelo.instance.del_correo(this)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}
