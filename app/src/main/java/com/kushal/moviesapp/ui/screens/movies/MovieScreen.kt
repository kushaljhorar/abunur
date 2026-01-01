package com.kushal.moviesapp.ui.screens.movies


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MovieScreen(moviesViewModel: MoviesViewModel) {

    val movies by moviesViewModel.movies.observeAsState(emptyList())
    var selectedGenre by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        moviesViewModel.getMovies()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        GenreDropdown { genre ->
            selectedGenre = genre   // ⭐ remember selected genre

            if (genre == null) {
                moviesViewModel.showAllMovies()
            } else {
                moviesViewModel.filterMovies(genre)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (movies.isEmpty()) {
            NoData()
        } else {
            LazyColumn {
                items(movies) { movie ->
                    MovieCard(
                        movie = movie,
                        selectedGenre = selectedGenre   // ⭐ pass down
                    )
                }
            }
        }
    }
}


