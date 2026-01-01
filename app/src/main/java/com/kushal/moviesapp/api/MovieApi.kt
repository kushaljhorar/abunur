package com.kushal.moviesapp.api


import com.kushal.moviesapp.model.Movie
import retrofit2.http.GET



interface MovieApi {

    @GET("movies")
    suspend fun getMovies():List<Movie>


}