package com.example.libimgur

import com.example.libimgur.apis.ImgurApiV3
import com.example.libimgur.models.GalleryResponse
import com.example.libimgur.models.TagsResponse
import com.example.libimgur.params.Section
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test
import retrofit2.Response

class ImgurApiV3Tests {

    val api = ImgurClient.buildApi(ImgurApiV3::class.java)

    @Test
    fun `GET - gallery working`() {
        runBlocking {
            // Now we can only pass Section enum class in arguments & cannot pass other things except that.
            val response: Response<GalleryResponse> = api.getGallery(section = Section.HOT, true)
            assertNotNull(response.body())
        }
    }

    @Test
    fun `GET - tags working`() {
        runBlocking {
            val response: Response<TagsResponse> = api.getTags()
            assertNotNull(response.body())
        }
    }
}