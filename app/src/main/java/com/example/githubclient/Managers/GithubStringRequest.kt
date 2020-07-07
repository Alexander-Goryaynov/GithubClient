package com.example.githubclient.Managers

import android.app.DownloadManager
import android.util.Base64
import android.widget.Toast
import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.lang.Exception
import javax.xml.transform.ErrorListener

class GithubStringRequest(url: String, _listener: Response.Listener<String>,
                          errorListener: Response.ErrorListener
) : StringRequest(Method.GET, url, _listener, errorListener) {

    val login = "Alexander-Goryaynov"
    val token = "566581bc54dd1b7b4a57825f87d29587fa381ba1"
    var listener: Response.Listener<String> = _listener

    override fun getHeaders(): MutableMap<String, String> {
        val headers = mutableMapOf<String, String>()
        val creds = "$login:$token"
        val auth = "Basic " + Base64.encodeToString(creds.toByteArray(), Base64.NO_WRAP)
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = auth
        return headers
    }

    override fun deliverResponse(response: String?) {
        listener.onResponse(response)
    }
}