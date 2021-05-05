package com.example.imguram.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imguram.R
import com.example.imguram.databinding.FeedListItemBinding
import com.example.libimgur.models.Image

class FeedListAdapter : ListAdapter<Image, FeedListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.equals(newItem)
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FeedListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = getItem(position)
        holder.binding.titleText.text = post.title
        holder.binding.imageView.load("https://i.imgur.com/${post.cover}.jpg") {
            placeholder(R.drawable.ic_placeholder_image_24)
            error(R.drawable.ic_placeholder_image_24)   // This drawable will show on error
        }
    }

    class ViewHolder(val binding: FeedListItemBinding) : RecyclerView.ViewHolder(binding.root)
}