package com.example.githubclient.AdaptersAndHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubclient.Models.Commit
import com.example.githubclient.R

class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textViewMessage: TextView = itemView.findViewById(R.id.textViewMessageId)
    private val textViewDate: TextView = itemView.findViewById(R.id.textViewDateId)
    private val imageView: ImageView = itemView.findViewById(R.id.imageViewId)
    private val textViewAuthorLogin: TextView = itemView.findViewById(R.id.textViewAuthorLoginId)

    fun bind(commit: Commit) {
        textViewMessage.text = if (commit.message == null || commit.message == "null") "отсутствует"
            else commit.message
        textViewDate.text = commit.date ?: "отсутствует"
        textViewAuthorLogin.text = commit.authorLogin ?: "неизвестен"
        Glide.with(itemView).load(commit.authorAvatarUrl).into(imageView)
    }
}