package com.example.libimgur.apis

import com.example.libimgur.params.Section
import com.example.libimgur.models.GalleryResponse
import com.example.libimgur.models.TagsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurApiV3 {

    @GET("gallery/{section}")
    suspend fun getGallery(
        @Path("section") section: Section,    // This section enum class should be converted to string
        @Query("album_previews") albumPreviews: Boolean? = true
    ): Response<GalleryResponse>
    // Function should be suspended if returning Response<T> rather than Call<T>

    @GET("tags")
    suspend fun getTags(): Response<TagsResponse>
}