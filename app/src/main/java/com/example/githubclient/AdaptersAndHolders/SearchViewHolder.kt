package com.example.githubclient.AdaptersAndHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubclient.Models.Repository
import com.example.githubclient.R

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.imageViewId)
    private val textViewName: TextView = itemView.findViewById(R.id.textViewNameId)
    private val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescriptionId)
    private val textViewLanguage: TextView = itemView.findViewById(R.id.textViewLanguageId)
    private val textViewForks: TextView = itemView.findViewById(R.id.textViewForksId)
    private val textViewStargazers: TextView = itemView.findViewById(R.id.textViewStargazersId)
    private val textViewOwnerLogin: TextView = itemView.findViewById(R.id.textViewOwnerLoginId)
    private val textViewAuthorId: TextView = itemView.findViewById(R.id.textViewAuthorId)
    fun bind(repos: Repository) {
        textViewName.text = repos.name
        textViewDescription.text = if (repos.description == null ||
            repos.description == "null") "" else repos.description
        textViewLanguage.text = if (repos.language == null ||
            repos.language == "null") "" else "Язык: ${repos.language}"
        textViewForks.text = "Форков: ${repos.forksCount.toString()}"
        textViewStargazers.text = "Звёзд: ${repos.stargazersCount.toString()}"
        Glide.with(itemView).load(repos.ownerAvatarUrl).into(imageView)
        textViewAuthorId.text = "Автор:"
        textViewOwnerLogin.text = repos.ownerLogin
        if (repos.name == "NothingHasBeenFound") {
            textViewName.text = "Ничего не найдено"
            textViewForks.text = ""
            textViewStargazers.text = ""
            textViewAuthorId.text = ""
        }
    }
}