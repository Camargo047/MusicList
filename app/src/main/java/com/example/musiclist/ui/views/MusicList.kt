package com.example.musiclist.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.musiclist.R
import com.example.musiclist.models.Music
import com.example.musiclist.ui.theme.MusicListTheme
import com.example.musiclist.viewmodels.MusicListViewModel

@Composable
fun MusicList(
    viewModel: MusicListViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    ){
    val uiState by viewModel.musicListUiState.collectAsState()

    LazyColumn{
        items(uiState.musicList){music ->
            MusicCard(
                music = music,
                onDelete = viewModel::deleteMusic,
                onEditMusic = {
                    viewModel.onEditMusic(
                        music = music,
                        navController = navController,
                    )
                },
                modifier = modifier
            )
        }
    }
}
@Composable
fun MusicCard(
    music: Music,
    onDelete: (Music) -> Unit,
    onEditMusic: () -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable{
                onEditMusic
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = music.picture),
                contentDescription = null,
                modifier = modifier
                    .size(32.dp)
                    .padding(start = 4.dp, end = 4.dp)
            )
            Column {
                Text(
                    text = music.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black

                )
                Text(
                    text = music.genre,
                    fontStyle = FontStyle.Italic,
                    fontSize = 15.sp,
                    color = Color.Black,
                    maxLines = 2,
                    modifier = modifier.width(260.dp)
                )
            }
            Spacer(modifier = modifier.weight(1F))
            Image(
                painter = painterResource(id = R.drawable.baseline_delete_outline_24),
                contentDescription = "delete",
                modifier
                    .padding(end = 5.dp)
                    .clickable{ onDelete(music) }
                )
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun CardPreview(){
    MusicListTheme {
        MusicCard(
            music = Music(
                R.drawable.baseline_music_note_24,
                "Teste",
                "Realizando testes na criação da lista"),{ }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ListPreview(){
    MusicListTheme {
        MusicList(viewModel())
    }
}
*/
