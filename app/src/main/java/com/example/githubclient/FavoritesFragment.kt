package com.example.githubclient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.githubclient.AdaptersAndHolders.SearchAdapter
import com.example.githubclient.Logic.FavoritesLogic

class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewId)
        recyclerView.addItemDecoration(DividerItemDecoration(this.activity, 1))
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipeId)
        swipe.setColorSchemeResources(R.color.colorPrimaryDark)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBarId)
        progressBar.visibility = ProgressBar.VISIBLE
        updateView(recyclerView)
        return view
    }

    private fun updateView(recyclerView: RecyclerView) {
        recyclerView.visibility = View.GONE
        val favorites = this.activity?.let { FavoritesLogic.getFavoritesList(it) }
        recyclerView.adapter = favorites?.let { SearchAdapter(it) }
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        recyclerView.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipeId)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewId)
        swipe.setOnRefreshListener {
            updateView(recyclerView)
            swipe.isRefreshing = false
        }
    }
}
