package com.example.imguram.ui.stories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imguram.R
import com.example.imguram.databinding.StoryPageItemBinding
import com.example.libimgur.models.Image

class StoryPagerAdapter : ListAdapter<Image, StoryPagerAdapter.ViewHolder>(
    StoriesDiffCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            StoryPageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = getItem(position)
        val imageUrl =
            if (image.isAlbum == true && image.imagesCount != 0) image.images?.get(0)?.link
            else image.link

        imageUrl?.let {
            holder.binding.storyImageView.load(imageUrl) {
                placeholder(R.drawable.ic_placeholder_image_24)
                error(R.drawable.ic_placeholder_image_24)
            }
        }
    }

    class ViewHolder(val binding: StoryPageItemBinding) : RecyclerView.ViewHolder(binding.root)

    private class StoriesDiffCallBack : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.equals(newItem)
        }
    }
}