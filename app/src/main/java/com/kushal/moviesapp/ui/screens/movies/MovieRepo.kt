package com.kushal.moviesapp.ui.screens.movies


import android.content.Context
import com.kushal.moviesapp.api.MoviesService
import com.kushal.moviesapp.local.MovieDatabase
import com.kushal.moviesapp.local.MovieEntity
import com.kushal.moviesapp.model.Movie

class MovieRepo(context: Context) {

    private val movieApi = MoviesService.movieApi
    private val movieDao =
        MovieDatabase.getDatabase(context).movieDao()

    // Fetch API → Save to Room
    suspend fun fetchAndSaveMovies(): List<Movie> {
        val apiMovies = movieApi.getMovies()

        val entities = apiMovies.map {
            MovieEntity(
                id = it.id,
                title = it.title,
                releaseDate = it.release_date,
                overview = it.overview,
                genres = it.genres.joinToString(","), // IMPORTANT
                url = it.url
            )
        }

        movieDao.insertMovies(entities)
        return apiMovies
    }

    // Room → All movies
    suspend fun getAllMovies(): List<Movie> {
        return movieDao.getAllMovies().map { it.toMovie() }
    }

    // Room → Filtered movies
    suspend fun getMoviesByGenre(genre: String): List<Movie> {
        return movieDao.getMoviesByGenre(genre).map { it.toMovie() }
    }
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        release_date = releaseDate,
        overview = overview,
        genres = genres.split(","),
        url = url
    )
}
