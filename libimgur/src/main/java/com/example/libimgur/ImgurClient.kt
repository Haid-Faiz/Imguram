package com.example.libimgur

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ImgurClient {

    const val API_KEY = "fdd7e6f958b0218"
    const val BASE_URL = "https://api.imgur.com/3/"

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(Interceptor {
            val newRequest = it.request()
                .newBuilder()
                .header("Authorization", "Client-ID $API_KEY")
                .build()
            it.proceed(newRequest)
        }).build()
    }

    val retrofit: Retrofit by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    // if you want to pass generic type in such a way that it can be used directly as a class inside
    // the function body then you need to make that function an inline function and along with that
    // you have to add kotlin `reified` keyword.

    // Because it's `T` only available at compile time but erased at runtime. but if make it inline
    // reified then `T` can be accessible at runtime also.

    inline fun <reified T> buildApi(api: T): T =                  // authToken: String? = null
        retrofit.create(T::class.java)

    //-------------------------------------------------------------------------------------------------
    // Here in this below case we can't get access of T inside the function itself
    // & if you want T to be accessible as a class inside the function also then
    // follow above method

//    fun <T> buildAPI(api: Class<T>) = retrofit.create(api)
}