package com.example.githubclient.Requests

import android.util.Base64
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

class GithubStringRequest(url: String,
                          _listener: Response.Listener<String>,
                          errorListener: Response.ErrorListener,
                          _login: String,
                          _password: String
) : StringRequest(Method.GET, url, _listener, errorListener) {
    private val login = _login
    private val password = _password
    var listener: Response.Listener<String> = _listener

    override fun getHeaders(): MutableMap<String, String> {
        val headers = mutableMapOf<String, String>()
        val creds = "$login:$password"
        val auth = "Basic " + Base64.encodeToString(creds.toByteArray(), Base64.NO_WRAP)
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = auth
        return headers
    }

    override fun deliverResponse(response: String?) {
        listener.onResponse(response)
    }
}