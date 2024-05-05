package com.example.musiclist.ui.views

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musiclist.R
import com.example.musiclist.viewmodels.MusicListViewModel

@Composable
fun InsertMusic(
    viewModel: MusicListViewModel,
    navController: NavController,
){
    BackHandler {
        viewModel.navigateBack(navController)
    }
    val uiState by viewModel.insertFormUiState.collectAsState()
    InsertForm(
        picture = uiState.picture,
        name = uiState.name,
        genre = uiState.genre,
        onUpdatePicture = viewModel::onPictureChange,
        onUpdateName = viewModel::onNameChange,
        onUpdateGenre = viewModel::onGenreChange
    )
}

@Composable
fun InsertForm(
    @DrawableRes picture: Int,
    name: String,
    genre: String,
    onUpdatePicture: (Int) -> Unit,
    onUpdateName: (String) -> Unit,
    onUpdateGenre: (String) -> Unit,
    modifier: Modifier = Modifier,
){
    val imageList = listOf(
        R.drawable.baseline_music_note_24,
        R.drawable.baseline_library_music_24,
        R.drawable.baseline_music_video_24
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.fillMaxWidth()
        ){

            items(imageList){image ->
                Box(
                    modifier = modifier
                        .size(100.dp)
                        .padding(8.dp)
                        .background(if (image == picture) Color.LightGray else Color.Transparent),
                    contentAlignment = Alignment.Center

                ){
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = modifier
                            .size(100.dp)
                            .clickable {
                                onUpdatePicture(image)
                            }
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(8.dp))
        TextField(
            value = name,
            onValueChange = onUpdateName,
            label = { Text(text = "Nome")},
            singleLine = true,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(8.dp))
        TextField(
            value = genre,
            onValueChange = onUpdateGenre,
            label = { Text(text = "Genre")},
            singleLine = false,
            minLines = 1,
            maxLines = 3,
            modifier = modifier.fillMaxWidth()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun InsertFormPreview(){
    InsertForm(R.drawable.baseline_music_note_24, "","", {},{},{})
}