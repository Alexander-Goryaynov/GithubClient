package com.example.githubclient.AdaptersAndHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.Models.Repository
import com.example.githubclient.R

class SearchAdapter(private val repos: List<Repository>
) : RecyclerView.Adapter<SearchViewHolder>() {

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val rootView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.search_item, parent, false)
        return SearchViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(repos[position])
    }
}