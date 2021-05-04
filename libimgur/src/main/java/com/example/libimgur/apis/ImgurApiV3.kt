package com.example.libimgur.apis

import com.example.libimgur.models.GalleryResponse
import com.example.libimgur.models.TagsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurApiV3 {

    @GET("gallery/{section}")
    fun getGallery(
        @Path("section") section: String,
        @Query("album_previews") albumPreviews: Boolean? = true
    ): Response<GalleryResponse>

    @GET("tags")
    fun getTags(): Response<TagsResponse>
}