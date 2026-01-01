package com.kushal.moviesapp.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MoviesService {
    private const val BASE_URL = "https://movies-app-backend.replit.app/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }

}