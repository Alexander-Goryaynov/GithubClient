package com.example.githubclient.AdaptersAndHolders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.Models.Commit
import com.example.githubclient.R

class InfoAdapter(private val commits: ArrayList<Commit>) : RecyclerView.Adapter<InfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val rootView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.info_item, parent, false)
        return InfoViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return commits.size
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(commits[position])
    }
}