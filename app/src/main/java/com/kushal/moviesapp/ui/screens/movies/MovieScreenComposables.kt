package com.kushal.moviesapp.ui.screens.movies

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kushal.moviesapp.model.Movie
import com.kushal.moviesapp.utils.genreColor

@Composable
fun GenreDropdown(onSelect: (String?) -> Unit) {

    val genres = listOf(
        "Comedy", "Crime", "Action", "Foreign", "Animation",
        "Science Fiction", "Drama", "War", "Documentary",
        "Romance", "Thriller", "Fantasy", "Horror",
        "Adventure", "Family", "Mystery"
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Show All") }

    Column {

        Text(
            text = "Filter by Genre",
            style = MaterialTheme.typography.subtitle2,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = selectedText,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {

            DropdownMenuItem(onClick = {
                selectedText = "Show All"
                expanded = false
                onSelect(null)
            }) {
                Text("Show All")
            }

            genres.forEach { genre ->
                DropdownMenuItem(onClick = {
                    selectedText = genre
                    expanded = false
                    onSelect(genre)
                }) {
                    Text(genre)
                }
            }
        }
    }
}



@Composable
fun MovieCard(movie: Movie, selectedGenre: String? = null) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(movie.url)
                )
                context.startActivity(intent)
            },
        elevation = 6.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = movie.title,
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Release Year: ${movie.release_date.take(4)}",
                style = MaterialTheme.typography.body2,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movie.overview,
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {

                val genresToShow = if (selectedGenre == null) {
                    movie.genres
                } else {
                    movie.genres.filter { it.equals(selectedGenre, ignoreCase = true) }
                }

                genresToShow.forEach { genre ->
                    GenreChip(genre)
                    Spacer(modifier = Modifier.width(6.dp))
                }
            }

        }
    }
}


@Composable
fun GenreChip(genre: String) {
    Box(
        modifier = Modifier
            .background(
                color = genreColor(genre),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = genre,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

@Composable
fun NoData() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No Movies Found")
    }
}