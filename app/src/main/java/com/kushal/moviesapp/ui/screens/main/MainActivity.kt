package com.kushal.moviesapp.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme

import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.kushal.moviesapp.ui.screens.movies.MovieScreen
import com.kushal.moviesapp.ui.screens.movies.MoviesViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val moviesViewModel: MoviesViewModel by viewModels()

        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieScreen(moviesViewModel)
                }
            }
        }
    }
}



