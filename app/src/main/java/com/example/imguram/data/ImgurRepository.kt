package com.example.imguram.data

import com.example.libimgur.ImgurClient
import com.example.libimgur.apis.ImgurApiV3
import com.example.libimgur.models.Gallery
import com.example.libimgur.models.Image
import com.example.libimgur.models.Tag
import com.example.libimgur.models.TagResponse
import com.example.libimgur.params.Section

class ImgurRepository {

    val api = ImgurClient.buildApi(ImgurApiV3::class.java)

    suspend fun getHotFeed(): List<Image>? {
        val response = api.getGallery(section = Section.HOT)
        return response.body()?.data
    }

    suspend fun getTrendingFeed(): List<Image>? {
        return api.getGallery(section = Section.TOP).body()?.data
    }

    suspend fun getTags(): List<Tag>? {
        return api.getTags().body()?.data?.tags
    }

    suspend fun getGalleryTag(tag: String): TagResponse? {
        return api.getGalleryTag(tag).body()
    }

}