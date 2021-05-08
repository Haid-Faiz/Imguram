package com.example.imguram.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imguram.R
import com.example.imguram.databinding.StoriesListItemBinding
import com.example.imguram.ui.stories.StoryActivity
import com.example.libimgur.models.Tag

class StoriesListAdapter : ListAdapter<Tag, StoriesListAdapter.ViewHolder>(
    StoriesDiffCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            StoriesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tag = getItem(position)
        holder.binding.storyHeadText.text = tag.displayName
        holder.binding.storyHeadImage.load("https://i.imgur.com/${tag.backgroundHash}.jpg") {
            placeholder(R.drawable.ic_placeholder_image_24)
            error(R.drawable.ic_placeholder_image_24)
        }
        holder.binding.root.apply {
            setOnClickListener {
                context.startActivity(
                    Intent(context, StoryActivity::class.java).putExtra("tag", tag.name)
                )
            }
        }
    }

    class ViewHolder(val binding: StoriesListItemBinding) : RecyclerView.ViewHolder(binding.root)

    private class StoriesDiffCallBack : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem.equals(newItem)
        }
    }
}


//class Balti(context: Context) : CalendarView(context) {
//
//
//    override fun setBackground(background: Drawable?) {
//        super.setBackground(background)
//    }
//
//    override fun setBackgroundColor(color: Int) {
//        super.setBackgroundColor(color)
//    }
//
//    override fun setWeekDayTextAppearance(resourceId: Int) {
//        super.setWeekDayTextAppearance(resourceId)
//    }
//}