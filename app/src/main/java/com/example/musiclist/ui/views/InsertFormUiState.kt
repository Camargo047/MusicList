package com.example.musiclist.ui.views

import androidx.annotation.DrawableRes
import com.example.musiclist.R

data class InsertFormUiState(
    @DrawableRes val picture: Int = R.drawable.baseline_music_note_24,
    val name: String = "",
    val genre: String = "",
)
