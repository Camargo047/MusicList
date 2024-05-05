package com.example.musiclist.ui.views

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.musiclist.R

data class AppMusicUiState(
    @StringRes val title: Int = R.string.lista_de_musicas,
    @DrawableRes val fabIcon: Int = R.drawable.baseline_add_circle_outline_24,
    @StringRes val iconContentDescription: Int = R.string.inserindo_nova_musica
)
