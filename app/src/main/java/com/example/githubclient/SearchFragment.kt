package com.example.githubclient

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.toolbox.Volley
import com.example.githubclient.Logic.SearchLogic
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        var buttonSearch = view.findViewById<Button>(R.id.buttonSearchId)
        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewId)
        val queue = Volley.newRequestQueue(this.activity)
        recyclerView.addItemDecoration(DividerItemDecoration(this.activity, 1))
        var swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipeId)
        swipe.setColorSchemeResources(R.color.colorPrimaryDark)
        buttonSearch.setOnClickListener {
            val query = editTextId.text.toString()
            if (query == "") {
                return@setOnClickListener
            }
            SearchLogic.getRepositories(query, this.activity!!, queue, recyclerView)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipeId)
        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewId)
        val queue = Volley.newRequestQueue(this.activity)
        swipe.setOnRefreshListener {
            val query = editTextId.text.toString()
            if (query != "") {
                SearchLogic.getRepositories(query, this.activity!!, queue, recyclerView)
            }
            swipe.isRefreshing = false
        }
    }

}
