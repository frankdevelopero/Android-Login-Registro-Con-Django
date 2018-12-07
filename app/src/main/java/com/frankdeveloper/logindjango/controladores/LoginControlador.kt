package com.frankdeveloper.logindjango.controladores

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.frankdeveloper.logindjango.pojos.Usuario
import com.frankdeveloper.logindjango.utils.UrlAPI
import com.frankdeveloper.logindjango.utils.VolleySingleton
import org.json.JSONException
import org.json.JSONObject


class LoginControlador {

    interface LoginCallback {
        fun enExito()
        fun enError(error: String)
    }

    @Throws(JSONException::class)
    fun login(context: Context, usuario: Usuario, callback: LoginCallback) {

        val jsonBody = JSONObject()
        jsonBody.put("email", usuario.email)
        jsonBody.put("password", usuario.password)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,
            UrlAPI.url_login, jsonBody,
            Response.Listener {
                try {
                    val obj = Usuario()
                    obj.email = it.getString("email")


                }catch (exception: JSONException){

                }
            },
            Response.ErrorListener {

            })

        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)

    }

}
