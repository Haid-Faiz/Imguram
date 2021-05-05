package com.example.libimgur

import com.example.libimgur.converters.EnumConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ImgurClient {

    private const val API_KEY = "fdd7e6f958b0218"
    private const val BASE_URL = "https://api.imgur.com/3/"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(Interceptor {
            val newRequest = it.request()
                .newBuilder()
                .header("Authorization", "Client-ID $API_KEY")
                .build()
            it.proceed(newRequest)
        }).build()
    }

    private val retrofit: Retrofit by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create()) // we have added out own enum to string converter
            .addConverterFactory(EnumConverterFactory())
            .build()
    }

    // if you want to pass generic type in such a way that it can be used directly as a class inside
    // the function body then you need to make that function an inline function and along with that
    // you have to add kotlin `reified` keyword.

    // Because it's `T` only available at compile time but erased at runtime. but if make it inline
    // reified then `T` can be accessible at runtime also.

//    inline fun <reified T> buildApi(api: T): T =                  // authToken: String? = null
//        retrofit.create(T::class.java)

    //-------------------------------------------------------------------------------------------------
    // Here in this below case we can't get access of T inside the function itself
    // & if you want T to be accessible as a class inside the function also then
    // follow above method

    fun <T> buildApi(api: Class<T>): T = retrofit.create(api)
}