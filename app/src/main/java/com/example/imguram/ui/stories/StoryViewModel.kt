package com.example.imguram.ui.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imguram.data.ImgurRepository
import com.example.libimgur.models.Image
import kotlinx.coroutines.launch

class StoryViewModel : ViewModel() {

    private val repo = ImgurRepository()

    private var _images: MutableLiveData<List<Image>> = MutableLiveData()
    var images: LiveData<List<Image>> = _images

    fun getTagImages(tag: String) = viewModelScope.launch {
        _images.postValue(repo.getTagImages(tag))
    }

}