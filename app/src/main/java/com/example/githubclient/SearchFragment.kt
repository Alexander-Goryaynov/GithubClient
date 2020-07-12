package com.example.githubclient

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.Volley
import com.example.githubclient.Logic.SearchLogic
import kotlinx.android.synthetic.main.fragment_search.*


/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        var buttonSearch = view.findViewById<Button>(R.id.buttonSearchId)
        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewId)
        val queue = Volley.newRequestQueue(this.activity)
        recyclerView.addItemDecoration(DividerItemDecoration(this.activity, 1))
        buttonSearch.setOnClickListener {
            val query = editTextId.text.toString()
            if (this.activity != null) {
                SearchLogic.getRepositories(query, this.activity!!, queue, recyclerView)
            }
        }
        return view
    }

}
