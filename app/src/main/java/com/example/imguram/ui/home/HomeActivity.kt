package com.example.imguram.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.Coil
import coil.request.ImageRequest
import com.example.imguram.R
import com.example.imguram.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val storiesViewModel by viewModels<HomeViewModel>()
    private var _binding: ActivityHomeBinding? = null
    private lateinit var storiesListAdapter: StoriesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(_binding?.root)
        setUpNav()
        _binding?.storiesRecyclerview?.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        storiesListAdapter = StoriesListAdapter()
        _binding?.storiesRecyclerview?.adapter = storiesListAdapter
        storiesViewModel.fetchStories()
        storiesViewModel.stories.observe(this) {
            // Images PreLoading stuff
            it.forEach { tag ->
                val imageRequest = ImageRequest.Builder(this)
                    .data("https://i.imgur.com/${tag.backgroundHash}.jpg")
                    .size(resources.getDimensionPixelSize(R.dimen.story_head_image_view))
                    .build()

                // Now we need image loader to enqueue this request
                Coil.imageLoader(this).enqueue(imageRequest)
            }

            storiesListAdapter.submitList(it)
        }
    }

    private fun setUpNav() {
        val navController = findNavController(R.id.nav_host_fragment)
        //------------------- action Bar code-------------------------
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//
//        val appBarConfiguration = AppBarConfiguration(setOf(
//            // These are fragments ids in mobile navigation nav graph
//                R.id.navigation_hot, R.id.navigation_trending))
//        setupActionBarWithNavController(navController, appBarConfiguration)
        //----------------Action Bar code------------------------------
        _binding?.bottomNavView?.setupWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}