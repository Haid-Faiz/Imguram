package com.example.imguram.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.imguram.R
import com.google.android.material.tabs.TabLayout

class FeedFragment : Fragment() {

    private lateinit var feedViewModel: FeedViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        feedViewModel =
                ViewModelProvider(this).get(FeedViewModel::class.java)
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feedName = arguments?.getString("feed")
//        view.findViewById<TextView>(R.id.text_home).text = feedName
    }
}