package com.example.musiclist.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musiclist.R
import com.example.musiclist.viewmodels.MusicListViewModel
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicApp(
    modifier: Modifier = Modifier
){
    val viewModel: MusicListViewModel = viewModel()
    val navController = rememberNavController()

    val uiState by viewModel.appMusicUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = uiState.title)
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.navigate(navController = navController)
            }) {
                Image(
                    painter = painterResource(id = uiState.fabIcon),
                    contentDescription = stringResource(
                        id = uiState.iconContentDescription)
                )
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = AppScreens.MusicList.name,
            modifier = modifier.padding(it)
        ){
            composable(route = AppScreens.MusicList.name){
                MusicList(viewModel = viewModel, navController)
            }
            composable(route = AppScreens.InsertMusic.name){
                InsertMusic(viewModel = viewModel, navController)
            }
        }
    }
}
enum class AppScreens{
    MusicList,
    InsertMusic,
}