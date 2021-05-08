package com.example.imguram

import android.app.Application
import android.os.Build.VERSION.SDK_INT
import coil.Coil
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

class ImgurApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Coil.setImageLoader(ImageLoader.Builder(this).componentRegistry {

            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder(this@ImgurApp))
            } else {
                add(GifDecoder())
            }
        }.build())
    }
}