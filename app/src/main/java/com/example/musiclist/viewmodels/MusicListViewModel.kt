package com.example.musiclist.viewmodels

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.musiclist.R
import com.example.musiclist.data.createMusicList
import com.example.musiclist.models.Music
import com.example.musiclist.ui.views.AppMusicUiState
import com.example.musiclist.ui.views.AppScreens
import com.example.musiclist.ui.views.InsertFormUiState
import com.example.musiclist.ui.views.MusicListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MusicListViewModel : ViewModel() {

    private val _musicListUiState: MutableStateFlow<MusicListUiState> =
        MutableStateFlow(MusicListUiState(createMusicList()))

    val musicListUiState: StateFlow<MusicListUiState> =
        _musicListUiState.asStateFlow()

    private val _insertFormUiState: MutableStateFlow<InsertFormUiState> =
        MutableStateFlow(InsertFormUiState())

    val insertFormUiState: StateFlow<InsertFormUiState> =
        _insertFormUiState.asStateFlow()

    private val _appMusicUiState: MutableStateFlow<AppMusicUiState> =
        MutableStateFlow(AppMusicUiState())

    val appMusicUiState: StateFlow<AppMusicUiState> =
        _appMusicUiState.asStateFlow()

    private var musicToEdit: Music = Music()
    private var editMusic: Boolean = false

    fun navigate(navController: NavController){
        if (_appMusicUiState.value.title == R.string.lista_de_musicas){
            _appMusicUiState.update{ currentState ->
                currentState.copy(
                    title = R.string.inserindo_musica,
                    fabIcon = R.drawable.baseline_check_24,
                    iconContentDescription = R.string.confirmar,
                )
            }
            navController.navigate(AppScreens.InsertMusic.name)
        }else{
            val musics: MutableList<Music> = _musicListUiState.value.musicList.toMutableList()
            if(editMusic){
                val pos = musics.indexOf(musicToEdit)
                musics.removeAt(pos)
                musics.add(pos, Music(
                    _insertFormUiState.value.picture,
                    _insertFormUiState.value.name,
                    _insertFormUiState.value.genre
                ))

            }else{
                musics.add(
                    Music(
                    picture = _insertFormUiState.value.picture,
                    name = _insertFormUiState.value.name,
                    genre = _insertFormUiState.value.genre
                    )
                )
                _musicListUiState.update{ currentState->
                    currentState.copy(
                        musicList = musics.toList()
                    )
                }
            }
            _insertFormUiState.update {
                InsertFormUiState()
            }
            _appMusicUiState.update{
               AppMusicUiState()
            }
            navController.navigate(AppScreens.MusicList.name){
                popUpTo(AppScreens.MusicList.name){
                    inclusive = true
                }
            }
        }
    }

    fun navigateBack(navController: NavController){
        _appMusicUiState.update{
            AppMusicUiState()
        }
        navController.popBackStack()
    }

    fun deleteMusic(music: Music){
        val musics: MutableList<Music> = _musicListUiState.value.musicList.toMutableList()
        musics.remove(music)
        _musicListUiState.value = _musicListUiState.value.copy(
            musicList = musics.toList()
        )
    }

    fun onEditMusic(music: Music, navController: NavController){
        editMusic = true
        musicToEdit = music
        _insertFormUiState.update { currentState ->
            currentState.copy(
                picture = musicToEdit.picture,
                name = musicToEdit.name,
                genre = musicToEdit.genre,
            )
        }
        _appMusicUiState.update { currentState ->
            currentState.copy(
                title = R.string.editar_musica,
                fabIcon = R.drawable.baseline_check_24,
                iconContentDescription = R.string.confirmar
            )
        }
        navController.navigate(route = AppScreens.InsertMusic.name)
    }

    fun onPictureChange(@DrawableRes picture: Int){
        _insertFormUiState.update { currentState ->
            currentState.copy(
                picture = picture
            )
        }
    }
    fun onNameChange(name: String){
        _insertFormUiState.update { currentState ->
            currentState.copy(
                name = name
            )
        }
    }
    fun onGenreChange(genre: String){
        _insertFormUiState.update { currentState ->
            currentState.copy(
                genre = genre
            )
        }
    }

}