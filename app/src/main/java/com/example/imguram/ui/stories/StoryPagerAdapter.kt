package com.example.imguram.ui.stories

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.load
import coil.request.ImageRequest
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
        // Preload the next image in advance
        cacheNext(position, holder.binding.storyImageView)
    }

    private fun cacheNext(position: Int, imageView: ImageView) {

        val image = try {
            getItem(position + 1)   // handling exception because it can give index out of bound exception
        } catch (e: Exception) {
            null
        }
        val imageUrl =
            if (image?.isAlbum == true && image.imagesCount != 0) image.images?.get(0)?.link
            else image?.link

        imageUrl?.let {
            val imageRequest = ImageRequest.Builder(imageView.context)
                .data(imageUrl)
//                .size(imageView.width)   // it is giving error that this size is zero
                .build()

            Coil.imageLoader(imageView.context).enqueue(imageRequest)
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