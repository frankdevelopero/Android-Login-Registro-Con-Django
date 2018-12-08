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
        fun enExito(usuario: Usuario)
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
                    obj.first_name = it.getString("first_name")
                    obj.last_name = it.getString("last_name")
                    obj.id = it.getInt("id")
                    callback.enExito(obj)

                }catch (exception: JSONException){
                    callback.enError(exception.toString())
                }
            },
            Response.ErrorListener {
                callback.enError(it.toString())
            })

        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)

    }

}
