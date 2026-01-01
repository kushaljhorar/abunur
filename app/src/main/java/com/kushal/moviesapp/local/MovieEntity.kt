
package com.kushal.moviesapp.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val genres: String, // Comedy,Crime
    val url: String
)


