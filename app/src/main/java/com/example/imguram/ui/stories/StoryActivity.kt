package com.example.imguram.ui.stories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.imguram.databinding.ActivityStoryBinding

class StoryActivity : AppCompatActivity() {

    private var _binding: ActivityStoryBinding? = null
    private val storyViewModel by viewModels<StoryViewModel>()
    private lateinit var storyPagerAdapter: StoryPagerAdapter
    private lateinit var onPageChangeCallback: ViewPager2.OnPageChangeCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        val handler = Handler(Looper.getMainLooper())
        val nextPageRunnable = Runnable {
            _binding!!.storyViewPager.currentItem++
        }
        val tag = intent.getStringExtra("tag")
        tag?.let {
            storyViewModel.getTagImages(it)
        }
        storyPagerAdapter = StoryPagerAdapter()
        _binding!!.storyViewPager.adapter = storyPagerAdapter

        onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // every page change will be invoked here including opening of the first page

                _binding!!.storyProgressView.animate()
                    .scaleX(1F)
                _binding!!.storyProgressView.animate()
                    .scaleX(1F)
                    .setDuration(5000)
                    .start()
                // here we will do handler - runnable stuff
                handler.removeCallbacks(nextPageRunnable)
                handler.postDelayed(nextPageRunnable, 5000)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        _binding!!.storyViewPager.registerOnPageChangeCallback(onPageChangeCallback)
    }

    override fun onStop() {
        super.onStop()
        _binding!!.storyViewPager.unregisterOnPageChangeCallback(onPageChangeCallback)
    }

    override fun onResume() {
        super.onResume()
        storyViewModel.images.observe(this) {
            // preloaded next image in adapter.... not here
            storyPagerAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}