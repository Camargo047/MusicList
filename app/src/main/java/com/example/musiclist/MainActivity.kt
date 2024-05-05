package com.example.musiclist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musiclist.ui.theme.MusicListTheme
import com.example.musiclist.ui.views.MusicApp
import com.example.musiclist.ui.views.MusicList
import com.example.musiclist.viewmodels.MusicListViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicListTheme {
                Surface(modifier = Modifier
                    .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                MusicApp()

                }
            }
        }
    }
}



