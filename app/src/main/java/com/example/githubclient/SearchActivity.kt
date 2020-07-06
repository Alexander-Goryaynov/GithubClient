package com.example.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.android.volley.toolbox.Volley
import com.example.githubclient.Logic.SearchLogic
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        title = "Поиск репозиториев"
        val queue = Volley.newRequestQueue(this)
        recyclerViewId.addItemDecoration(DividerItemDecoration(this, 1))
        buttonSearchId.setOnClickListener {
            val query = editTextId.text.toString()
            SearchLogic.getRepositories(query, this, queue, recyclerViewId)
        }
    }

}
