package com.kushal.moviesapp.utils

import androidx.compose.ui.graphics.Color

fun genreColor(genre: String): Color {
    return when (genre.lowercase()) {
        "comedy" -> Color(0xFFFFE082)
        "crime" -> Color(0xFFEF9A9A)
        "action" -> Color(0xFF90CAF9)
        "foreign" -> Color(0xFFB39DDB)
        "animation" -> Color(0xFFFFCC80)
        "science fiction" -> Color(0xFF80DEEA)
        "drama" -> Color(0xFFA5D6A7)
        "war" -> Color(0xFFBCAAA4)
        "documentary" -> Color(0xFFB0BEC5)
        "romance" -> Color(0xFFF48FB1)
        "thriller" -> Color(0xFFCE93D8)
        "fantasy" -> Color(0xFFD1C4E9)
        "horror" -> Color(0xFFB71C1C)
        "adventure" -> Color(0xFF81C784)
        "family" -> Color(0xFFFFF59D)
        "mystery" -> Color(0xFF9FA8DA)
        else -> Color(0xFFBDBDBD)
    }
}