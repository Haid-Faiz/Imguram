package com.example.imguram.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imguram.data.ImgurRepository
import com.example.libimgur.models.Gallery
import com.example.libimgur.models.Tag
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val imgur = ImgurRepository()

    private var _stories: MutableLiveData<List<Tag>> = MutableLiveData()
    var stories: LiveData<List<Tag>> = _stories

    fun fetchStories() = viewModelScope.launch {
        _stories.postValue(imgur.getTags())
    }
}