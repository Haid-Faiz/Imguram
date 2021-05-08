package com.example.imguram.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.request.ImageRequest
import com.example.imguram.databinding.FragmentFeedBinding
import com.example.libimgur.params.Section

class FeedFragment : Fragment() {

    private val feedViewModel: FeedViewModel by activityViewModels()   // Check to not use sharedViewModel becoz

    // layout shifting was happening in arnav's tutorial
    private var _binding: FragmentFeedBinding? = null
    private lateinit var feedListAdapter: FeedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feedName = arguments?.getString("feed")
        when (feedName) {
            "hot" -> feedViewModel.getFeedUpdate(Section.HOT)
            "top" -> feedViewModel.getFeedUpdate(Section.TOP)
        }
        feedListAdapter = FeedListAdapter()
        _binding!!.feedRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        _binding!!.feedRecyclerview.addItemDecoration(DividerItemDecoration(requireContext(),
            DividerItemDecoration.VERTICAL))
        _binding!!.feedRecyclerview.adapter = feedListAdapter
    }

    override fun onResume() {
        super.onResume()
        feedViewModel.feed.observe(viewLifecycleOwner) {

            it.forEach { image ->
                val imageRequest = ImageRequest.Builder(requireContext())
                    .data("https://i.imgur.com/${image.cover}.jpg")
                    .size(_binding!!.feedRecyclerview.width)
                    .build()

                Coil.imageLoader(requireContext()).enqueue(imageRequest)
            }

            feedListAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}