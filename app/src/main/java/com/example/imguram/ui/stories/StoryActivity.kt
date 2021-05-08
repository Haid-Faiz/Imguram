package com.example.imguram.ui.stories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.imguram.R
import com.example.imguram.databinding.ActivityStoryBinding

class StoryActivity : AppCompatActivity() {

    private var _binding: ActivityStoryBinding? = null
    private val storyViewModel by viewModels<StoryViewModel>()
    private lateinit var storyPagerAdapter: StoryPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        val tag = intent.getStringExtra("tag")
        tag?.let {
            storyViewModel.getTagImages(it)
        }
        storyPagerAdapter = StoryPagerAdapter()
        _binding!!.storyViewPager.adapter = storyPagerAdapter
    }

    override fun onResume() {
        super.onResume()
        storyViewModel.images.observe(this) {
            storyPagerAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}