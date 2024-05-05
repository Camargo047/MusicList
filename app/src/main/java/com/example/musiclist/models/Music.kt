package com.example.musiclist.models

import androidx.annotation.DrawableRes
import com.example.musiclist.R

data class Music(
    @DrawableRes val picture: Int = R.drawable.baseline_music_note_24,
    val name: String = "",
    val genre: String = "",
)
