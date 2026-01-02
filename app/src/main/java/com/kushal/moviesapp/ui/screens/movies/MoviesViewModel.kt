package com.kushal.moviesapp.ui.screens.movies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kushal.moviesapp.model.Movie
import kotlinx.coroutines.launch

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepo = MovieRepo(application)

    private val _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies: LiveData<List<Movie>> = _movies


    fun getMovies() {
        viewModelScope.launch {
            try {
                movieRepo.fetchAndSaveMovies()
                _movies.value = movieRepo.getAllMovies()
            } catch (e: Exception) {
                Log.e("FetchMovies", e.message.toString())
            }
        }
    }


    fun showAllMovies() {
        viewModelScope.launch {
            _movies.value = movieRepo.getAllMovies()
        }
    }


    fun filterMovies(genre: String) {
        viewModelScope.launch {
            _movies.value = movieRepo.getMoviesByGenre(genre)
        }
    }
}
