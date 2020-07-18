package com.example.githubclient

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubclient.AdaptersAndHolders.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val fragmentAdapter = MainPagerAdapter(supportFragmentManager)
        viewPagerId.adapter = fragmentAdapter
        tabsId.setupWithViewPager(viewPagerId)
    }

}
