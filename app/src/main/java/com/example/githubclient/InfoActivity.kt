package com.example.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.githubclient.Logic.InfoLogic
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    companion object {
        const val NAME = "NAME"
        const val OWNER_LOGIN = "OWNER_LOGIN"
        const val OWNER_AVATAR_URL = "OWNER_AVATAR_URL"
        const val COMMITS_URL = "COMMITS_URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        setupActionBar()
        val queue = Volley.newRequestQueue(this)
        setDataViews(queue)
        recyclerViewId.addItemDecoration(DividerItemDecoration(this, 1))
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
        title = "Информация о репозитории"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setDataViews(queue: RequestQueue) {
        textViewNameId.text = intent?.extras?.getString(NAME)
        Glide.with(this).load(intent?.extras?.getString(OWNER_AVATAR_URL)).into(imageViewId)
        textViewOwnerLoginId.text = intent?.extras?.getString(OWNER_LOGIN)
        intent.extras?.getString(COMMITS_URL)?.let { InfoLogic.getCommits(it, this,
            queue, recyclerViewId) }
    }
}
