package com.example.githubclient.AdaptersAndHolders

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubclient.InfoActivity
import com.example.githubclient.InfoActivity.Companion.COMMITS_URL
import com.example.githubclient.InfoActivity.Companion.NAME
import com.example.githubclient.InfoActivity.Companion.OWNER_AVATAR_URL
import com.example.githubclient.InfoActivity.Companion.OWNER_LOGIN
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
    private val textViewAuthorId: TextView = itemView.findViewById(R.id.textViewMessageLabelId)

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
        itemView.setOnClickListener {
            openInfoActivity(itemView.context, repos)
        }
    }

    private fun openInfoActivity(context: Context, repos: Repository) {
        if (repos.name == "NothingHasBeenFound") {
            return
        }
        val intent = Intent(context, InfoActivity::class.java)
        intent.putExtra("NAME", repos.name)
        intent.putExtra("COMMITS_URL", repos.commitsUrl)
        intent.putExtra("OWNER_AVATAR_URL", repos.ownerAvatarUrl)
        intent.putExtra("OWNER_LOGIN", repos.ownerLogin)
        context.startActivity(intent)
    }
}