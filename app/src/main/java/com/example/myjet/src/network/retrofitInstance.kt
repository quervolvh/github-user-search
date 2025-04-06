package com.example.myjet.src.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL:String = "https://api.github.com/"

    // setup a client with logging
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { message ->
                println("LOG-APP: $message")
            }).apply {
            level= HttpLoggingInterceptor.Level.BODY
        })
        .build()

    // instantiate a Retrofit instance with gson as the data converter
    private val retrofit = Retrofit.Builder()
        .addConverterFactory( GsonConverterFactory.create() )
        .baseUrl(BASE_URL)
        .client(httpClient)
        .build()

    // update this to return an instance of the Retrofit instance associated
    // with your base url
    val retrofitService: GithubUserApi by lazy {
        retrofit.create(GithubUserApi::class.java)
    }

}
