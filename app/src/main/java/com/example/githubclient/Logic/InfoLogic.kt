package com.example.githubclient.Logic

import android.content.Context
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.example.githubclient.AdaptersAndHolders.InfoAdapter
import com.example.githubclient.Requests.GithubStringRequest
import com.example.githubclient.Models.Commit
import org.json.JSONArray
import org.json.JSONObject

class InfoLogic {

    companion object {

        fun getCommits(url: String, context: Context, queue: RequestQueue,
                       recyclerView: RecyclerView, progressBar: ProgressBar) {
            val creds = AuthorizationLogic.getSavedCreds(context)
            val request = GithubStringRequest(
                url,
                Response.Listener {
                        response -> makeView(recyclerView, parseCommitsResponse(response), context)
                        progressBar.visibility = ProgressBar.GONE
                },
                Response.ErrorListener {
                    Toast.makeText(context,"Ошибка запроса", Toast.LENGTH_LONG).show()
                    progressBar.visibility = ProgressBar.GONE
                },
                creds.first,
                creds.second
            )
            queue.add(request)
        }

        private fun parseCommitsResponse(response: String): ArrayList<Commit> {
            val list = ArrayList<Commit>()
            val jsonArr = JSONArray(response)
            for (i in 0 until jsonArr.length()) {
                if (i > 9){
                    break
                }
                val item = jsonArr.getJSONObject(i)
                val commit = item.getJSONObject("commit")
                val commitMessage = commit.getString("message")
                var commitAuthor : JSONObject
                var commitDate: String? = null
                var authorLogin: String? = null
                var authorAvatarUrl: String? = null
                if (!commit.isNull("author") && !item.isNull("author")) {
                    commitAuthor = commit.getJSONObject("author")
                    commitDate = commitAuthor.getString("date").
                        replace("T", " ").replace("Z", " ")
                    authorLogin = commitAuthor.getString("name")
                    authorAvatarUrl = item.getJSONObject("author").
                        getString("avatar_url")
                }
                list.add(Commit(commitMessage, commitDate, authorLogin, authorAvatarUrl))
            }
            return list
        }

        private fun makeView(recyclerView: RecyclerView,
                             list: ArrayList<Commit>, context: Context) {
            recyclerView.adapter = InfoAdapter(list)
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }
}