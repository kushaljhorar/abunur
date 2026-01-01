package com.kushal.moviesapp.model

data class Movie(
    val id: String,
    val release_date: String,
    val title: String,
    val url: String,
    val overview: String,
    val genres: List<String>

)

