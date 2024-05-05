package com.example.musiclist.ui.views

import com.example.musiclist.models.Music

data class MusicListUiState(
    val musicList: List<Music> = listOf()
)
