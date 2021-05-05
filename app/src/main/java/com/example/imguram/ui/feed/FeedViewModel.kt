package com.example.imguram.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imguram.data.ImgurRepository
import com.example.libimgur.models.Image
import com.example.libimgur.params.Section
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    val repo = ImgurRepository()
    private var _feed: MutableLiveData<List<Image>> = MutableLiveData()
    var feed: LiveData<List<Image>> = _feed

    fun getFeedUpdate(section: Section) = viewModelScope.launch {
        when (section) {
            Section.HOT -> _feed.postValue(repo.getHotFeed())
            Section.TOP -> _feed.postValue(repo.getTrendingFeed())
        }
    }
}
