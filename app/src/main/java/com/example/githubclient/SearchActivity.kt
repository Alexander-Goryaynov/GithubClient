package com.example.githubclient

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.githubclient.AdaptersAndHolders.MainPagerAdapter
import com.example.githubclient.Logic.AuthorizationLogic
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val fragmentAdapter = MainPagerAdapter(supportFragmentManager)
        viewPagerId.adapter = fragmentAdapter
        tabsId.setupWithViewPager(viewPagerId)
    }

    override fun onBackPressed() { }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemLogoutId -> {
                AuthorizationLogic.deleteCreds(this)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
