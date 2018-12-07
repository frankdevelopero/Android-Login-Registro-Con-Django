package com.frankdeveloper.logindjango.controladores

import android.content.Context
import com.frankdeveloper.logindjango.pojos.Usuario

class RegistroControlador {

    interface RegistroCallback {
        fun enExito(usuario: Usuario)
        fun enError(error: String)
    }


    fun registro(
        context: Context, usuario: Usuario,
        callback: RegistroCallback
    ) {


    }

}
