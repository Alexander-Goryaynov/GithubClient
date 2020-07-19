package com.example.githubclient.Requests

import android.util.Base64
import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException

class GithubJsonRequest(
    url: String,
    _listener: Response.Listener<JSONObject>,
    errorListener: Response.ErrorListener,
    _login: String,
    _password: String
) : Request<JSONObject>(Method.GET, url, errorListener) {
    private val login = _login
    private val password = _password
    private var listener: Response.Listener<JSONObject> = _listener

    override fun getHeaders(): MutableMap<String, String> {
        val headers = mutableMapOf<String, String>()
        val creds = "$login:$password"
        val auth = "Basic " + Base64.encodeToString(creds.toByteArray(), Base64.NO_WRAP)
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = auth
        return headers
    }

    override fun parseNetworkResponse(response: NetworkResponse?): Response<JSONObject> {
        return try {
            val jsonString = java.lang.String(
                response!!.data,
                HttpHeaderParser.parseCharset(response.headers)
            )
            Response.success(
                JSONObject(jsonString.toString()),
                HttpHeaderParser.parseCacheHeaders(response)
            )
        } catch (e: UnsupportedEncodingException) {
            Response.error(ParseError(e))
        } catch (je: JSONException) {
            Response.error(ParseError(je))
        }
    }

    override fun deliverResponse(response: JSONObject?) {
        listener.onResponse(response)
    }
}