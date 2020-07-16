package com.example.githubclient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.AdaptersAndHolders.SearchAdapter
import com.example.githubclient.Logic.FavoritesLogic

class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewId)
        recyclerView.addItemDecoration(DividerItemDecoration(this.activity, 1))
        updateView(recyclerView)
        return view
    }

    private fun updateView(recyclerView: RecyclerView) {
        var favorites = this.activity?.let { FavoritesLogic.getFavoritesList(it) }
        recyclerView.adapter = favorites?.let { SearchAdapter(it) }
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
    }
}
