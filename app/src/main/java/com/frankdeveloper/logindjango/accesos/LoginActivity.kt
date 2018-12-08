package com.frankdeveloper.logindjango.accesos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.frankdeveloper.logindjango.MainActivity
import com.frankdeveloper.logindjango.R
import com.frankdeveloper.logindjango.controladores.LoginControlador
import com.frankdeveloper.logindjango.modelos.UsuarioModelo
import com.frankdeveloper.logindjango.pojos.Usuario
import com.frankdeveloper.logindjango.utils.ProgressDialogSingleton
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val TAG = "LoginActivity"
    var loginControlador: LoginControlador ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginControlador = LoginControlador()
        listenOnClick()
    }

    private fun listenOnClick() {
        btnGoRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            if (!edtEmail.text.isEmpty() && !edtPassword.text.isEmpty()){
                val usuario = Usuario()
                usuario.email = edtEmail.text.toString()
                usuario.password = edtPassword.text.toString()
                login(usuario)

            }else
                Toast.makeText(this, "Ingresa tu correo y contraseña", Toast.LENGTH_SHORT).show()
        }

    }


    private fun login(usuario: Usuario){
        val progressDialog = ProgressDialogSingleton.showProgressDialog(this, "Iniciando sesión..")
        loginControlador!!.login(this, usuario, object : LoginControlador.LoginCallback{
            override fun enExito(usuario: Usuario) {
                 save(usuario)
                ProgressDialogSingleton.hideProgressDialog(progressDialog)
            }

            override fun enError(error: String) {
                 Log.e(TAG, error)
                ProgressDialogSingleton.hideProgressDialog(progressDialog)
            }

        })
    }

    private fun save(usuario: Usuario){

        //Guardamos los datos del usuario y procedemos ir a la actividad principal

        UsuarioModelo.instance.set_id(this, usuario.id)
        UsuarioModelo.instance.set_correo(this, usuario.email!!)
        UsuarioModelo.instance.set_nombre(this, usuario.first_name!!)
        UsuarioModelo.instance.set_apellido(this, usuario.last_name!!)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
