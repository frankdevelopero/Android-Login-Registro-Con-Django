package com.frankdeveloper.logindjango.accesos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.frankdeveloper.logindjango.R
import com.frankdeveloper.logindjango.controladores.RegistroControlador
import com.frankdeveloper.logindjango.pojos.Usuario
import com.frankdeveloper.logindjango.utils.ProgressDialogSingleton
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    var registroControlador: RegistroControlador ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registroControlador = RegistroControlador()

        listenOnClick()

    }

    private fun listenOnClick() {
        btnGoLogin.setOnClickListener {
            finish()
        }

        btnRegister.setOnClickListener {
            if (!edt_first_name.text.isEmpty() && !edt_last_name.text.isEmpty()
                && !edt_email.text.isEmpty() && !edt_password.text.isEmpty()){

                val usuario = Usuario()
                usuario.first_name = edt_first_name.text.toString()
                usuario.last_name = edt_last_name.text.toString()
                usuario.email = edt_email.text.toString()
                usuario.password = edt_password.text.toString()

                register(usuario)

            }else
                Toast.makeText(applicationContext, "Llene todos los campos para registrarte", Toast.LENGTH_SHORT).show()
        }

    }

    private fun register(usuario: Usuario){
        val progressDialog = ProgressDialogSingleton.showProgressDialog(this, "Iniciando sesión..")
        registroControlador!!.registro(this, usuario, object :RegistroControlador.RegistroCallback{
            override fun enExito() {
                Toast.makeText(applicationContext, "Cuenta creado con éxito", Toast.LENGTH_SHORT).show()
                ProgressDialogSingleton.hideProgressDialog(progressDialog)
                finish()
            }

            override fun enError(error: String) {
                ProgressDialogSingleton.hideProgressDialog(progressDialog)
                Toast.makeText(applicationContext, "Error: $error", Toast.LENGTH_SHORT).show()
            }

        })
    }

}
