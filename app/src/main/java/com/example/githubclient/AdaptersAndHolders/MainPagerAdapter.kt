package com.example.githubclient.AdaptersAndHolders

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.githubclient.FavoritesFragment
import com.example.githubclient.SearchFragment

class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SearchFragment()
            else -> FavoritesFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Поиск"
            else -> "Избранное"
        }
    }
}