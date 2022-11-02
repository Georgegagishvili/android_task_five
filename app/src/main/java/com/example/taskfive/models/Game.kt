package com.example.taskfive.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GAMES")
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "GAME_TITLE") val title: String,
    @ColumnInfo(name = "GAME_DESCRIPTION") val description: String,
    @ColumnInfo(name = "RELEASE_YEAR") val releaseYear: String,
    @ColumnInfo(name = "IMAGE_URL") val imageUrl: String,
) {
    companion object {
        val dummyData: ArrayList<Game> = arrayListOf(
            Game(
                0,
                "Warframe",
                "Awaken as an unstoppable warrior and battle" +
                        " alongside your friends" +
                        " in this story-driven free-to-play" +
                        " online action game",
                "2013",
                "https://i.imgur.com/n92cMXo.png"
            ),
            Game(
                1,
                "Mortal Kombat X",
                "Mortal Kombat X is a 2015 fighting video game developed" +
                        " by NetherRealm Studios and published by Warner Bros. Interactive " +
                        "Entertainment for Microsoft Windows, PlayStation 4, and Xbox One." +
                        " It is the sequel to 2011's installment, " +
                        "Mortal Kombat, taking place 25 years later after the events of its predecessor.",
                "2015",
                "https://i.imgur.com/oJmR1eZ.png"
            ),
            Game(
                2,
                "Dota 2",
                "Dota is a series of strategy video games now developed by Valve. " +
                        "The series began in 2003 with the release of Defense of the Ancients," +
                        " a fan-developed multiplayer online battle" +
                        " arena mod for the video game Warcraft III: Reign of " +
                        "Chaos and its expansion, The Frozen Throne.",
                "2012",
                "https://i.imgur.com/qdYAtn3.png"
            ),
        )
    }
}


