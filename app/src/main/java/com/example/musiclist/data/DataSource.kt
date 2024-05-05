package com.example.musiclist.data

import com.example.musiclist.models.Music
import com.example.musiclist.R

fun createMusicList(): List<Music> {
    val pictures = listOf(
        R.drawable.baseline_library_music_24,
        R.drawable.baseline_music_note_24,
        R.drawable.baseline_music_video_24
    )
    val char = listOf(
        "Shape of You" to "Pop",
        "Bohemian Rhapsody" to "Rock",
        "Lose Yourself" to "Hip Hop",
        "So What" to "Jazz",
        "Scary Monsters and Nice Sprites" to "Electronic",
        "Für Elise" to "Classical",
        "No Woman, No Cry" to "Reggae",
        "Take Me Home, Country Roads" to "Country",
        "The Thrill is Gone" to "Blues",
        "Uptown Funk" to "Funk",
        "Superstition" to "R&B",
        "Master of Puppets" to "Metal",
        "Ho Hey" to "Indie",
        "A Change is Gonna Come" to "Soul",
        "The Times They Are a-Changin'" to  "Folk",
        "Smells Like Teen Spirit" to  "Punk",
        "Levels" to "EDM",
        "Smells Like Teen Spirit" to "Alternative"
    )
    val music = char.mapIndexed{index, (name, genre)->
        Music(picture = pictures[index % pictures.size], name = name, genre = genre)
    }
    return music
}