package com.frankdeveloper.logindjango.controladores

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.frankdeveloper.logindjango.pojos.Usuario
import com.frankdeveloper.logindjango.utils.UrlAPI
import com.frankdeveloper.logindjango.utils.VolleySingleton
import org.json.JSONObject
import android.util.Log




class RegistroControlador {

    interface RegistroCallback {
        fun enExito()
        fun enError(error: String)
    }


    fun registro(context: Context, usuario: Usuario, callback: RegistroCallback) {

        val jsonBody = JSONObject()
        jsonBody.put("first_name", usuario.first_name)
        jsonBody.put("last_name", usuario.last_name)
        jsonBody.put("email", usuario.email)
        jsonBody.put("password", usuario.password)
        Log.e("Json Send", jsonBody.toString())
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, UrlAPI.url_users, jsonBody,
            Response.Listener {
                callback.enExito()

        }, Response.ErrorListener {
            callback.enError(it.toString())
        })

        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)

    }


}
