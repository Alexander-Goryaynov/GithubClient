package com.example.githubclient.Logic

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.example.githubclient.Models.RealmCredentials
import com.example.githubclient.Requests.GithubJsonRequest
import com.example.githubclient.SearchActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import org.json.JSONObject

class AuthorizationLogic {

    companion object {

        fun checkCreds(login: String, password: String, context: Context, queue: RequestQueue) {
            val request = GithubJsonRequest(
                "https://api.github.com/rate_limit",
                Response.Listener {
                    response ->
                        if (parseRateLimit(response) > 60) {
                            saveCreds(context, login, password)
                            context.startActivity(Intent(context, SearchActivity::class.java))
                        } else {
                            Toast.makeText(context, "Неверные данные",
                                Toast.LENGTH_LONG).show()
                        }
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Неверные данные или ошибка соединения",
                        Toast.LENGTH_LONG).show()
                },
                login,
                password
            )
            queue.add(request)
        }

        private fun parseRateLimit(jsonObject: JSONObject): Int {
            val resources = jsonObject.getJSONObject("resources")
            val core = resources.getJSONObject("core")
            return core.getInt("limit")
        }

        fun saveCreds(context: Context, _login: String, _password: String) {
            var creds = RealmCredentials()
            creds = creds.apply {
                login = _login
                password = _password
            }
            initRealm(context)
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.copyToRealm(creds)
            realm.commitTransaction()
            realm.close()
        }

        fun anyCreds(context: Context): Boolean {
            initRealm(context)
            val realm = Realm.getDefaultInstance()
            val query = realm.where(RealmCredentials::class.java).findFirst()
            val result = (query != null)
            realm.close()
            return result
        }

        fun getSavedCreds(context: Context): Pair<String, String> {
            initRealm(context)
            val realm = Realm.getDefaultInstance()
            val query = realm.where(RealmCredentials::class.java).findFirst()
            val result = Pair(query?.login!!, query.password)
            realm.close()
            return result
        }

        private fun initRealm(context: Context) {
            Realm.init(context)
            val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
            Realm.setDefaultConfiguration(config)
        }
    }
}